import java.io.File; // Still needed for report directory path construction
import java.util.List;
import java.util.Map;
import java.util.HashMap;
// Removed: import java.util.Scanner; // No longer used

public class DSPMApp {

    private static void printUsage() {
        System.err.println("Usage: java DSPMApp <hostname> <username> <remoteDirectory> <authMethod> <passwordOrKeyPath> [port]");
        System.err.println("authMethod: 'password' or 'key'");
        System.err.println("port: defaults to 22 if not provided");
        System.err.println("Example (password): java DSPMApp myhost myuser /var/log password mypassword");
        System.err.println("Example (key): java DSPMApp myhost myuser /data key /path/to/id_rsa 2222");
    }

    public static void main(String[] args) {
        System.out.println("Starting DSPM Application (SSH Mode)...");

        if (args.length < 5 || args.length > 6) {
            printUsage();
            System.exit(1);
        }

        String hostname = args[0];
        String username = args[1];
        String remoteDirectory = args[2];
        String authMethod = args[3];
        String passwordOrKeyPath = args[4];
        int port = 22; // Default port

        if (args.length == 6) {
            try {
                port = Integer.parseInt(args[5]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid port number '" + args[5] + "'. Port must be an integer.");
                printUsage();
                System.exit(1);
            }
        }

        if (!"password".equalsIgnoreCase(authMethod) && !"key".equalsIgnoreCase(authMethod)) {
            System.err.println("Error: Invalid authMethod. Must be 'password' or 'key'.");
            printUsage();
            System.exit(1);
        }

        System.out.println("Parsed SSH Parameters:");
        System.out.println("  Hostname: " + hostname);
        System.out.println("  Username: " + username);
        System.out.println("  Remote Directory: " + remoteDirectory);
        System.out.println("  Auth Method: " + authMethod);
        if ("password".equalsIgnoreCase(authMethod)) {
            System.out.println("  Password provided: Yes");
        } else {
            System.out.println("  Key Path: " + passwordOrKeyPath);
        }
        System.out.println("  Port: " + port);

        SSHManager sshManager = new SSHManager(hostname, port, username);
        boolean connected = false;
        boolean scanSuccessful = false;

        try {
            // 1. Connect via SSH
            System.out.println("Attempting to connect to " + hostname + "...");
            if ("password".equalsIgnoreCase(authMethod)) {
                connected = sshManager.connectWithPassword(passwordOrKeyPath);
            } else { // "key"
                connected = sshManager.connectWithPublicKey(passwordOrKeyPath);
            }

            if (!connected) {
                // SSHManager already logs detailed errors for connection failures.
                // JSchException is re-thrown and caught below, so this specific check might be redundant
                // if all connection failures result in JSchException.
                // System.err.println("SSH connection failed. Please check credentials, server details, and JSch logs.");
                // System.exit(1); // Exit if connection cannot be established (will be handled by JSchException catch)
                // If connect methods don't throw but return false, this is still needed.
                // Given SSHManager throws JSchException on connect failure, this block might not be strictly necessary.
                // However, keeping it for defense in case connectWith methods change to not throw.
                 System.err.println("Failed to establish SSH connection. Check logs from SSHManager for details.");
                 System.exit(1);
            }
            System.out.println("Successfully connected to " + hostname + ".");

            // 2. Instantiate Refactored Components
            RemoteFileScanner remoteFileScanner = new RemoteFileScanner();
            DataDetector dataDetector = new DataDetector(); // Stateless
            ReportGenerator reportGenerator = new ReportGenerator();

            // 3. Perform Remote Scan
            System.out.println("Starting remote file scan on " + hostname + " in directory: " + remoteDirectory);
            List<String> remoteFilePaths = remoteFileScanner.scanRemoteFiles(sshManager, remoteDirectory);

            if (remoteFilePaths == null || remoteFilePaths.isEmpty()) {
                System.out.println("No remote files found to scan in " + remoteDirectory + " or an error occurred during listing.");
                // Proceed to generate an empty report
            } else {
                System.out.println(remoteFilePaths.size() + " remote file(s) found for scanning.");
            }

            // 4. Detect Sensitive Data Remotely
            Map<String, List<String>> allFindings = new HashMap<>();
            if (remoteFilePaths != null) { // Ensure list is not null before iterating
                System.out.println("Starting sensitive data detection for remote files...");
                for (String remoteFilePath : remoteFilePaths) {
                    System.out.println("Scanning remote file: " + remoteFilePath + "...");
                    List<String> fileFindings = dataDetector.detectSensitiveData(remoteFilePath, sshManager);
                    if (fileFindings != null && !fileFindings.isEmpty()) {
                        allFindings.put(remoteFilePath, fileFindings);
                    }
                }
                System.out.println("Sensitive data detection complete for remote files.");
            }

            // 5. Generate Report
            String reportDir = "JavaDSPM/reports";
            System.out.println("Generating report in directory: " + new File(reportDir).getAbsolutePath());
            reportGenerator.generateReport(allFindings, reportDir);

            scanSuccessful = true;

        } catch (com.jcraft.jsch.JSchException e) {
            // SSHManager's connect methods already log specific details.
            // DSPMApp just needs to note that the SSH operation failed.
            System.err.println("SSH Operation Failed: " + e.getMessage());
            // No need for e.printStackTrace() here as SSHManager should have logged specifics.
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during the DSPM scan process: " + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            // 6. Disconnect SSH Session
            if (sshManager != null) { // Ensure sshManager was instantiated
                System.out.println("Disconnecting SSH session...");
                sshManager.disconnect();
            }
            if (scanSuccessful) {
                System.out.println("DSPM Scan finished successfully.");
            } else {
                System.err.println("DSPM Scan finished with errors or was incomplete. Please check the console output for details.");
            }
        }
    }
}
