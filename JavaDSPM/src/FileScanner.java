import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileScanner {

    public List<File> scanFiles(String directoryPath) {
        System.out.println("Attempting to scan directory: " + directoryPath);
        List<File> foundFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.err.println("Error: Directory or file does not exist: " + directoryPath + ". Skipping scan.");
            return foundFiles;
        }

        if (!directory.isDirectory()) {
            System.err.println("Error: Provided path is not a directory: " + directoryPath + ". Skipping scan.");
            return foundFiles;
        }
        
        try {
            scanDirectoryRecursively(directory, foundFiles);
            System.out.println("Successfully completed scan for directory: " + directoryPath + ". Found " + foundFiles.size() + " file(s).");
        } catch (SecurityException e) {
            System.err.println("Error: Permission denied to access directory: " + directoryPath + ". " + e.getMessage() + ". Skipping scan.");
            return new ArrayList<>(); // Return empty list on security exception
        }
        return foundFiles;
    }

    private void scanDirectoryRecursively(File directory, List<File> foundFiles) {
        try {
            File[] files = directory.listFiles();
            if (files == null) { // Should be caught by Objects.requireNonNull previously, but good for robustness
                System.err.println("Warning: Cannot list files in directory " + directory.getAbsolutePath() + " (null returned). Skipping this directory.");
                return;
            }
            for (File file : files) {
                if (file.isFile()) {
                    foundFiles.add(file);
                } else if (file.isDirectory()) {
                    scanDirectoryRecursively(file, foundFiles);
                }
            }
        } catch (SecurityException e) {
            System.err.println("Error: Permission denied to access subdirectory: " + directory.getAbsolutePath() + ". " + e.getMessage() + ". Skipping this subdirectory.");
        } catch (NullPointerException e) { // Should not happen with prior null check, but as a safeguard
            System.err.println("Error: Null pointer encountered while listing files in: " + directory.getAbsolutePath() + ". " + e.getMessage() + ". Skipping this directory.");
        }
    }

    /*
    public static void main(String[] args) {
        FileScanner scanner = new FileScanner();
        String currentDirectory = "."; // Scan the current directory

        System.out.println("Scanning directory: " + new File(currentDirectory).getAbsolutePath());
        List<File> files = scanner.scanFiles(currentDirectory);

        if (files.isEmpty()) {
            System.out.println("No files found or error occurred.");
        } else {
            System.out.println("Found files:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
    */
}
