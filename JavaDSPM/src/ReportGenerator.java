import java.io.BufferedWriter;
import java.io.File; // Still needed for report directory and file creation
import java.io.FileWriter;
import java.io.IOException;
// Removed: import java.nio.file.Files; // No longer needed as main method is commented out
// Removed: import java.nio.file.Path; // No longer needed
// Removed: import java.nio.file.Paths; // No longer needed
import java.text.SimpleDateFormat;
import java.util.Date;
// Removed: import java.util.HashMap; // No longer needed as main method is commented out
import java.util.List;
import java.util.Map;
import java.util.ArrayList; // Keep for new ArrayList<>() in main if it were adapted

public class ReportGenerator {

    public void generateReport(Map<String, List<String>> findings, String reportDirectory) {
        // Ensure the report directory exists
        File dir = new File(reportDirectory);
        if (!dir.exists()) {
            System.out.println("Report directory " + reportDirectory + " does not exist. Attempting to create it.");
            if (!dir.mkdirs()) {
                System.err.println("Error: Could not create report directory: " + reportDirectory + ". Please check permissions or path.");
                return;
            }
        }

        // Generate timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "DSPM_Report_" + timeStamp + ".txt";
        File reportFile = new File(reportDirectory, reportFileName);

        System.out.println("Starting report generation. Report will be saved to: " + reportFile.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            // Report Header
            writer.write("DSPM Scan Report - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.newLine();
            writer.newLine();

            if (findings == null || findings.isEmpty()) {
                writer.write("No sensitive data found in the scanned files.");
                writer.newLine();
            } else {
                for (Map.Entry<String, List<String>> entry : findings.entrySet()) {
                    String remoteFilePath = entry.getKey(); // Key is now String
                    List<String> fileFindings = entry.getValue();

                    writer.write("File: " + remoteFilePath); // Use the remote file path string directly
                    writer.newLine();

                    if (fileFindings == null || fileFindings.isEmpty()) {
                        writer.write("  No sensitive data found in this file.");
                        writer.newLine();
                    } else {
                        for (String finding : fileFindings) {
                            writer.write("  - " + finding);
                            writer.newLine();
                        }
                    }
                    writer.newLine(); // Add a blank line between files
                }
            }
            System.out.println("Report generated successfully: " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing report to " + reportFile.getAbsolutePath() + ": " + e.getMessage() + ". Report generation failed.");
        }
    }

    /*
    // Main method is commented out as its sample data used java.io.File as keys,
    // which is incompatible with the new Map<String, List<String>> signature.
    // A proper test would involve creating a Map with String keys.
    //
    // public static void main(String[] args) {
    //     ReportGenerator reportGenerator = new ReportGenerator();
    //     String testReportDir = "JavaDSPM/reports_test";
    //
    //     // 1. Create sample findings data
    //     Map<File, List<String>> sampleFindings = new HashMap<>(); // Old: Map<File, List<String>>
    //
    //     // Mock File 1 (doesn't need to exist for this test)
    //     File file1 = new File("/path/to/dummy/document.txt");
    //     List<String> findings1 = new ArrayList<>();
    //     findings1.add("Credit Card Number found at line 10: 1234-5678-9012-3456");
    //     findings1.add("Email Address found at line 25: test@example.com");
    //     sampleFindings.put(file1, findings1);
    //
    //     // Mock File 2
    //     File file2 = new File("/another/path/to/confidential.docx");
    //     List<String> findings2 = new ArrayList<>();
    //     findings2.add("SSN found at line 5: 000-11-2222");
    //     sampleFindings.put(file2, findings2);
    //
    //     // Mock File 3 (no findings)
    //     File file3 = new File("/some/other/file.log");
    //     sampleFindings.put(file3, new ArrayList<>());
    //
    //
    //     // 2. Define a test report directory (already done as testReportDir)
    //
    //     // 3. Instantiate ReportGenerator and call generateReport
    //     System.out.println("Generating sample report in: " + testReportDir);
    //     // reportGenerator.generateReport(sampleFindings, testReportDir); // This line would now cause a compilation error
    //
    //     // 4. Print message (done within generateReport)
    //
    //     // 5. After generation, list contents or read report
    //     File reportDirFile = new File(testReportDir);
    //     File[] generatedFiles = reportDirFile.listFiles((dir, name) -> name.startsWith("DSPM_Report_") && name.endsWith(".txt"));
    //
    //     File latestReport = null;
    //     if (generatedFiles != null && generatedFiles.length > 0) {
    //         latestReport = generatedFiles[0]; // Assuming the first one is the one we just made (or the latest)
    //         for (File f : generatedFiles) {
    //             if (f.lastModified() > latestReport.lastModified()) {
    //                 latestReport = f;
    //             }
    //         }
    //
    //         System.out.println("\n--- Generated Report Content (" + latestReport.getName() + ") ---");
    //         try {
    //             List<String> lines = Files.readAllLines(latestReport.toPath());
    //             for (String line : lines) {
    //                 System.out.println(line);
    //             }
    //         } catch (IOException e) {
    //             System.err.println("Error reading generated report: " + e.getMessage());
    //         }
    //         System.out.println("--- End of Report Content ---\n");
    //     } else {
    //         System.err.println("Could not find generated report file to read.");
    //     }
    //
    //     // 6. Clean up
    //     if (latestReport != null && latestReport.exists()) {
    //         if (latestReport.delete()) {
    //             System.out.println("Test report file deleted: " + latestReport.getName());
    //         } else {
    //             System.err.println("Error deleting test report file: " + latestReport.getName());
    //         }
    //     }
    //
    //     // Delete the test directory if it's empty
    //     if (reportDirFile.exists()) {
    //         File[] remainingFiles = reportDirFile.listFiles();
    //         if (remainingFiles == null || remainingFiles.length == 0) {
    //              if (reportDirFile.delete()) {
    //                 System.out.println("Test report directory deleted: " + testReportDir);
    //             } else {
    //                 System.err.println("Error deleting test report directory: " + testReportDir);
    //             }
    //         } else {
    //              System.out.println("Test report directory not empty, not deleting: " + testReportDir);
    //              // Optionally, list remaining files for debugging
    //              for(File f : remainingFiles) {
    //                  System.out.println("Remaining file: " + f.getName());
    //              }
    //         }
    //     }
    // }
    */
}
