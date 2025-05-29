import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class DSPMApp {

    public static void main(String[] args) {
        System.out.println("Starting DSPM Scan...");
        Scanner inputScanner = new Scanner(System.in);
        String targetDirectoryPath = null;
        boolean success = false;

        try {
            // 1. Get Target Directory
            if (args.length > 0) {
                targetDirectoryPath = args[0];
                System.out.println("Using directory from command line argument: " + targetDirectoryPath);
            } else {
                System.out.print("Enter the directory path to scan: ");
                targetDirectoryPath = inputScanner.nextLine();
            }

            if (targetDirectoryPath == null || targetDirectoryPath.trim().isEmpty()) {
                System.err.println("Error: Directory path cannot be empty. Aborting scan.");
                return;
            }

            File targetDirectory = new File(targetDirectoryPath);
            System.out.println("Target directory: " + targetDirectory.getAbsolutePath());

            if (!targetDirectory.exists() || !targetDirectory.isDirectory()) {
                System.err.println("Error: Invalid directory path or directory does not exist: " + targetDirectory.getAbsolutePath() + ". Aborting scan.");
                return;
            }

            // 2. Instantiate Components
            FileScanner fileScanner = new FileScanner();
            DataDetector dataDetector = new DataDetector();
            ReportGenerator reportGenerator = new ReportGenerator();

            // 3. Scan Files
            System.out.println("Initiating file scanning...");
            List<File> filesToScan = fileScanner.scanFiles(targetDirectoryPath);

            if (filesToScan.isEmpty()) {
                System.out.println("No files found to scan in the specified directory.");
                success = true; // No files to scan is not an error in itself.
                return;
            }
            System.out.println("Number of files to scan: " + filesToScan.size());

            // 4. Detect Sensitive Data
            Map<File, List<String>> allFindings = new HashMap<>();
            System.out.println("Starting sensitive data detection process...");

            for (File file : filesToScan) {
                // Logging for individual file scanning is now in DataDetector.java
                List<String> fileFindings = dataDetector.detectSensitiveData(file);
                if (fileFindings != null && !fileFindings.isEmpty()) {
                    allFindings.put(file, fileFindings);
                }
            }
            System.out.println("Sensitive data detection process complete.");

            // 5. Generate Report
            if (allFindings.isEmpty()) {
                System.out.println("No sensitive data found.");
            } else {
                System.out.println("Sensitive data found. Generating report...");
            }
            
            String reportDirectory = "JavaDSPM/reports"; // Predefined report directory
            // Logging for report generation start/end is in ReportGenerator.java
            reportGenerator.generateReport(allFindings, reportDirectory);
            System.out.println("Report has been saved in the directory: " + new File(reportDirectory).getAbsolutePath());

            success = true;

        } catch (Exception e) {
            System.err.println("An unexpected error occurred during the DSPM scan: " + e.getMessage());
            e.printStackTrace(System.err); // Print stack trace for detailed debugging
        } finally {
            if (success) {
                System.out.println("DSPM Scan finished successfully.");
            } else {
                System.err.println("DSPM Scan finished with errors. Please check the console output for details.");
            }
            inputScanner.close();
        }
    }
}
