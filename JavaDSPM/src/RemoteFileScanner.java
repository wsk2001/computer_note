import java.util.ArrayList;
import java.util.List;

// Note: Removed java.io.File and java.util.Objects imports as they are no longer needed.

public class RemoteFileScanner {

    public List<String> scanRemoteFiles(SSHManager sshManager, String remoteBaseDirectory) {
        System.out.println("Attempting to scan remote directory: " + remoteBaseDirectory + " via SSH.");
        List<String> foundFiles = new ArrayList<>();

        if (sshManager == null) {
            System.err.println("Error: SSHManager instance is null. Cannot scan remote files.");
            return foundFiles;
        }
        if (remoteBaseDirectory == null || remoteBaseDirectory.trim().isEmpty()) {
            System.err.println("Error: Remote base directory path cannot be null or empty.");
            return foundFiles;
        }

        try {
            foundFiles = sshManager.listRemoteFilesRecursive(remoteBaseDirectory);
            if (foundFiles == null) { // SSHManager.listRemoteFilesRecursive might return null on internal error
                System.err.println("Error: Failed to list remote files. The SSHManager returned null for path: " + remoteBaseDirectory);
                return new ArrayList<>(); // Return empty list
            }
            System.out.println("Successfully scanned remote directory: " + remoteBaseDirectory + ". Found " + foundFiles.size() + " file(s).");
        } catch (Exception e) { // Catch any unexpected exceptions from SSHManager though it's designed to handle its own
            System.err.println("Error during remote file scanning for path " + remoteBaseDirectory + ": " + e.getMessage());
            e.printStackTrace(System.err);
            return new ArrayList<>(); // Return empty list on exception
        }
        return foundFiles;
    }

    /*
    // Old main method from FileScanner - kept commented for reference if needed, but not functional for RemoteFileScanner
    public static void main(String[] args) {
        // FileScanner scanner = new FileScanner(); // This would need to be RemoteFileScanner
        // String currentDirectory = ".";
        // System.out.println("Scanning directory: " + new File(currentDirectory).getAbsolutePath());
        // List<File> files = scanner.scanFiles(currentDirectory);
        // if (files.isEmpty()) {
        //     System.out.println("No files found or error occurred.");
        // } else {
        //     System.out.println("Found files:");
        //     for (File file : files) {
        //         System.out.println(file.getName());
        //     }
        // }
    }
    */
}
