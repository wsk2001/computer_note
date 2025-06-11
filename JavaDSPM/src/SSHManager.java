import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SSHManager {

    private JSch jsch;
    private Session session;
    private String hostname;
    private int port;
    private String username;
    private static final int DEFAULT_SSH_TIMEOUT = 20000; // 20 seconds

    public SSHManager(String hostname, int port, String username) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.jsch = new JSch();
    }

    private void configureSession(Session session) {
        // Disable strict host key checking.
        // IMPORTANT: For production environments, this is a security risk.
        // Host key checking should be enabled and managed properly.
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
    }

    public boolean connectWithPassword(String password) throws JSchException {
        try {
            session = jsch.getSession(username, hostname, port);
            session.setPassword(password);
            configureSession(session);

            System.out.println("Attempting SSH connection to " + username + "@" + hostname + ":" + port + " with password...");
            session.connect(DEFAULT_SSH_TIMEOUT);
            System.out.println("SSH connection to " + username + "@" + hostname + " successful.");
            return session.isConnected();
        } catch (JSchException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Auth fail") || errorMessage.contains("Auth cancel")) {
                System.err.println("Authentication failed for " + username + "@" + hostname + ". Check password or SSH server configuration (e.g., PasswordAuthentication setting).");
            } else if (errorMessage.contains("Connection timed out")) {
                System.err.println("SSH connection to " + hostname + " timed out after " + (DEFAULT_SSH_TIMEOUT / 1000) + " seconds.");
            } else {
                System.err.println("Error connecting to " + username + "@" + hostname + " with password: " + errorMessage);
            }
            throw e;
        }
    }

    public boolean connectWithPublicKey(String privateKeyPath) throws JSchException {
        try {
            jsch.addIdentity(privateKeyPath); // Load private key
            session = jsch.getSession(username, hostname, port);
            configureSession(session);

            System.out.println("Attempting SSH connection to " + username + "@" + hostname + ":" + port + " with public key: " + privateKeyPath);
            session.connect(DEFAULT_SSH_TIMEOUT);
            System.out.println("SSH connection to " + username + "@" + hostname + " successful.");
            return session.isConnected();
        } catch (JSchException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Auth fail") || errorMessage.contains("Auth cancel") || errorMessage.contains("USERAUTH_FAIL")) {
                System.err.println("Authentication failed for " + username + "@" + hostname + " using key " + privateKeyPath + ". Check key, key permissions, or SSH server configuration (e.g., PubkeyAuthentication setting).");
            } else if (errorMessage.contains("Connection timed out")) {
                System.err.println("SSH connection to " + hostname + " timed out after " + (DEFAULT_SSH_TIMEOUT / 1000) + " seconds.");
            } else if (errorMessage.contains("invalid privatekey")) {
                 System.err.println("Invalid private key format or incorrect key for " + username + "@" + hostname + ". Path: " + privateKeyPath);
            } else {
                System.err.println("Error connecting to " + username + "@" + hostname + " with public key " + privateKeyPath + ": " + errorMessage);
            }
            throw e;
        }
    }

    public String executeCommand(String command) {
        if (session == null || !session.isConnected()) {
            System.err.println("Error: SSH session to " + hostname + " is not connected. Cannot execute command.");
            return null;
        }

        ChannelExec channel = null;
        StringBuilder outputBuffer = new StringBuilder();
        StringBuilder errorBuffer = new StringBuilder();

        try {
            System.out.println("Executing remote command on " + hostname + ": " + command);
            try {
                channel = (ChannelExec) session.openChannel("exec");
            } catch (JSchException e) {
                System.err.println("Error opening 'exec' channel on " + hostname + " for command '" + command + "': " + e.getMessage());
                return null;
            }
            channel.setCommand(command);
            channel.setInputStream(null);

            InputStream stdout = channel.getInputStream();
            InputStream stderr = channel.getErrStream();

            channel.connect();

            // Read stdout
            try (BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout))) {
                String line;
                while ((line = stdoutReader.readLine()) != null) {
                    outputBuffer.append(line).append("\n");
                }
            }

            // Read stderr
            try (BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr))) {
                String line;
                while ((line = stderrReader.readLine()) != null) {
                    errorBuffer.append(line).append("\n");
                }
            }

            // Wait for the command to finish
            while (!channel.isClosed()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted while waiting for command completion.");
                    return null;
                }
            }

            int exitStatus = channel.getExitStatus();
            System.out.println("Remote command exit status: " + exitStatus);

            if (errorBuffer.length() > 0) {
                System.err.println("Remote command error output:\n" + errorBuffer.toString().trim());
            }

            if (exitStatus == 0) {
                 System.out.println("Remote command on " + hostname + " executed successfully: " + command);
            } else {
                 System.err.println("Remote command on " + hostname + " finished with non-zero exit status: " + exitStatus + " for command: " + command);
                 // Error buffer would have already been printed if not empty
            }

            return outputBuffer.toString().trim();

        } catch (JSchException | IOException e) { // IOException for stream reading
            System.err.println("Error during execution or I/O for command '" + command + "' on " + hostname + ": " + e.getMessage());
            // e.printStackTrace(System.err); // Avoid full stack trace for common operational errors unless debugging
            return null;
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public String readFileContent(String remoteFilePath) {
        if (remoteFilePath == null || remoteFilePath.trim().isEmpty()) {
            System.err.println("Error: Remote file path cannot be empty for readFileContent.");
            return null;
        }
        // Basic protection against command injection, though paths should be validated upstream.
        if (remoteFilePath.contains(";") || remoteFilePath.contains("|") || remoteFilePath.contains("&")) {
            System.err.println("Error: Invalid characters in remote file path: " + remoteFilePath);
            return null;
        }
        return executeCommand("cat \"" + remoteFilePath + "\"");
    }

    public List<String> listRemoteFilesRecursive(String remotePath) {
        if (remotePath == null || remotePath.trim().isEmpty()) {
            System.err.println("Error: Remote path cannot be empty for listRemoteFilesRecursive.");
            return new ArrayList<>();
        }
         // Basic protection against command injection.
        if (remotePath.contains(";") || remotePath.contains("|") || remotePath.contains("&")) {
            System.err.println("Error: Invalid characters in remote path: " + remotePath);
            return new ArrayList<>();
        }

        String command = "find \"" + remotePath + "\" -type f";
        String commandOutput = executeCommand(command);

        if (commandOutput != null && !commandOutput.isEmpty()) {
            // Split by newline and collect into a list
            // Trim to remove potential empty strings from split if output ends with newline
            String[] filesArray = commandOutput.split("\n");
            List<String> files = new ArrayList<>();
            for (String filePath : filesArray) {
                if (filePath != null && !filePath.trim().isEmpty()) {
                    files.add(filePath.trim());
                }
            }
            return files;
        }
        return new ArrayList<>(); // Return empty list if no output or error
    }

    public void disconnect() {
        if (session != null && session.isConnected()) {
            System.out.println("Disconnecting SSH session to " + hostname);
            session.disconnect();
        }
        System.out.println("SSH session disconnected.");
    }

    // UserInfo class for JSch, e.g., to handle prompts (not extensively used here for simplicity)
    // Could be expanded for more interactive scenarios or if keys are passphrase protected.
    private static class DefaultUserInfo implements UserInfo {
        private String password;

        public DefaultUserInfo(String password) {
            this.password = password;
        }

        public DefaultUserInfo() {}


        @Override
        public String getPassphrase() { return null; } // For passphrase-protected keys

        @Override
        public String getPassword() { return password; }

        @Override
        public boolean promptPassword(String message) { return true; } // Assume password is provided if needed

        @Override
        public boolean promptPassphrase(String message) { return true; } // Assume passphrase is provided if needed

        @Override
        public boolean promptYesNo(String message) { return true; } // Auto-accept prompts (e.g., host key) - use with caution

        @Override
        public void showMessage(String message) { System.out.println("JSch Message: " + message); }
    }
}
