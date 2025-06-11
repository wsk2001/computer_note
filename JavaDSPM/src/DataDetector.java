// Removed: import java.io.BufferedReader;
// Removed: import java.io.BufferedWriter;
// Removed: import java.io.File;
// Removed: import java.io.FileReader;
// Removed: import java.io.FileWriter;
// Removed: import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDetector {

    // Regex patterns for sensitive data
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("\\b(?:\\d[ -]*?){13,16}\\b");
    private static final Pattern SSN_PATTERN = Pattern.compile("\\b\\d{3}-\\d{2}-\\d{4}\\b");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");

    public List<String> detectSensitiveData(String remoteFilePath, SSHManager sshManager) {
        System.out.println("Attempting to detect sensitive data in remote file: " + remoteFilePath);
        List<String> detectionMessages = new ArrayList<>();

        if (sshManager == null) {
            System.err.println("Error: SSHManager instance is null. Cannot detect data for remote file: " + remoteFilePath);
            return detectionMessages;
        }
        if (remoteFilePath == null || remoteFilePath.trim().isEmpty()) {
            System.err.println("Error: Remote file path cannot be null or empty.");
            return detectionMessages;
        }

        String fileContent = null;
        try {
            fileContent = sshManager.readFileContent(remoteFilePath);
        } catch (Exception e) { // Catching a broad exception in case sshManager.readFileContent itself throws something unexpected
            System.err.println("Error obtaining content from remote file " + remoteFilePath + " via SSHManager: " + e.getMessage());
            e.printStackTrace(System.err);
            return detectionMessages;
        }

        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("No content found or error reading remote file: " + remoteFilePath + ". Skipping data detection for this file.");
            return detectionMessages;
        }

        String[] lines = fileContent.split("\\r?\\n"); // Split content into lines
        int lineNumber = 0;

        try {
            for (String line : lines) {
                lineNumber++;

                Matcher ccMatcher = CREDIT_CARD_PATTERN.matcher(line);
                while (ccMatcher.find()) {
                    detectionMessages.add(String.format("Credit Card Number found at line %d: %s", lineNumber, ccMatcher.group()));
                }

                Matcher ssnMatcher = SSN_PATTERN.matcher(line);
                while (ssnMatcher.find()) {
                    detectionMessages.add(String.format("SSN found at line %d: %s", lineNumber, ssnMatcher.group()));
                }

                Matcher emailMatcher = EMAIL_PATTERN.matcher(line);
                while (emailMatcher.find()) {
                    detectionMessages.add(String.format("Email Address found at line %d: %s", lineNumber, emailMatcher.group()));
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during regex processing for file " + remoteFilePath + ": " + e.getMessage());
            e.printStackTrace(System.err);
            // Optionally, could return partial findings or clear them based on policy
        }

        System.out.println("Finished sensitive data detection for remote file: " + remoteFilePath + ". Found " + detectionMessages.size() + " potential item(s).");
        return detectionMessages;
    }

    /*
    // Main method is commented out as it's designed for local file testing and
    // the detectSensitiveData method now requires an SSHManager instance.
    // public static void main(String[] args) {
    //     File testFile = new File("test_sensitive_data.txt");
    //
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
    //         writer.write("This is a test line with an email: test@example.com\n");
    //         writer.write("Credit card: 1234-5678-9012-3456 and another 9876543210987654\n");
    //         writer.write("My SSN is 000-11-2222.\n");
    //         writer.write("This line has no sensitive data.\n");
    //         writer.write("Another email: user.name+tag@subdomain.example.co.uk\n");
    //         writer.write("Invalid SSN: 123-45-678 but valid one is 111-22-3333\n");
    //         writer.write("Card:4444555566667777\n");
    //     } catch (IOException e) {
    //         System.err.println("Error creating test file: " + e.getMessage());
    //         return;
    //     }
    //
    //     DataDetector detector = new DataDetector();
    //     // System.out.println("Scanning file: " + testFile.getAbsolutePath());
    //     // List<String> results = detector.detectSensitiveData(testFile); // This line would now cause a compilation error
    //
    //     if (results.isEmpty()) { // 'results' would not be initialized here
    //         System.out.println("No sensitive data found.");
    //     } else {
    //         System.out.println("Sensitive data found:");
    //         for (String result : results) {
    //             System.out.println(result);
    //         }
    //     }
    //
    //     // Clean up the test file
    //     if (testFile.exists()) {
    //         if (testFile.delete()) {
    //             System.out.println("Test file deleted successfully.");
    //         } else {
    //             System.err.println("Error deleting test file.");
    //         }
    //     }
    // }
    */
}
