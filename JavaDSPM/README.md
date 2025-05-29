# Simple Java DSPM Tool

## Description

This tool is a basic Data Security Posture Management (DSPM) utility built in Java. It scans a specified directory and its subdirectories for files, attempts to detect predefined sensitive data patterns (Credit Card Numbers, SSNs, Email Addresses) using regular expressions, and generates a text-based report of its findings.

## Features

*   **Directory Scanning:** Recursively scans the specified directory to find all files.
*   **Basic Sensitive Data Detection:** Identifies potential occurrences of:
    *   Credit Card Numbers
    *   Social Security Numbers (SSNs)
    *   Email Addresses
*   **Text-based Reporting:** Generates a plain text report detailing which files contain potential sensitive data and what was found.

## Directory Structure

*   `src/`: Contains all the Java source code files (`.java`).
*   `reports/`: Default directory where the scan reports are saved.

## Prerequisites

*   Java Development Kit (JDK) installed (e.g., JDK 8 or later).

## How to Compile

To compile the source code, navigate to the `src` directory and use `javac`:

```bash
cd JavaDSPM/src
javac *.java
```

This will generate `.class` files for each Java source file.

## How to Run

After successful compilation, you can run the application from the `src` directory:

```bash
cd JavaDSPM/src
java DSPMApp [optional_directory_to_scan]
```

*   If `[optional_directory_to_scan]` is provided, the tool will scan that directory.
    *   Example: `java DSPMApp /path/to/your/data`
    *   Example (Windows): `java DSPMApp C:\Users\YourUser\Documents`
*   If `[optional_directory_to_scan]` is omitted, the program will prompt you to enter a directory path.
    *   Example (interactive): `java DSPMApp`

## Report Output

Scan reports are saved as text files in the `JavaDSPM/reports/` directory. The filename includes a timestamp to ensure uniqueness:

`DSPM_Report_YYYYMMDD_HHMMSS.txt`

Example: `DSPM_Report_20231027_143000.txt`

## Limitations

*   This is a basic tool for demonstration purposes and not a comprehensive Data Security Posture Management solution.
*   Sensitive data detection is based on simple regular expressions and may produce false positives or miss complex cases (e.g., data embedded in images, password-protected files, or non-standard formats).
*   The tool only processes plain text content within files. It does not parse complex file types like PDFs, Word documents, or Excel spreadsheets for sensitive data.
*   Does not encrypt data or provide any remediation capabilities.
*   Error handling is basic.
*   File scanning and processing are performed sequentially and may be slow for very large directories or files.

## Disclaimer

Use at your own risk. Always test thoroughly in a safe, non-production environment before using on any sensitive data. The authors are not responsible for any data loss or security incidents that may arise from the use of this tool.
