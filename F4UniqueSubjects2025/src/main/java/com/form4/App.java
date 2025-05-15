package com.form4;

import com.form4.service.CsvImportService;
import com.form4.util.DatabaseConnection;

/**
 * Main application class for F4 Subject Choices Importer
 * 
 * TODO: Implement proper configuration management (move DB credentials to properties file)
 * TODO: Add proper exception handling and logging
 * TODO: Consider implementing a command-line interface for better usability
 * TODO: Add startup validation (check DB connection, file existence, etc.)
 */
public class App {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/f4subjectchoices";
        String dbUser = "root";
        String dbPassword = "Blue@1963";
        String csvFile = "src/main/resources/data/F4_subject_choices.csv";

        try {
            DatabaseConnection dbConnection = new DatabaseConnection(jdbcURL, dbUser, dbPassword);
            CsvImportService importService = new CsvImportService(csvFile, dbConnection);
            importService.importData();
            System.out.printf("Processed %d students with %d choices%n", 
                importService.getTotalStudents(), 
                importService.getTotalChoices());
        } catch (Exception e) {
            System.err.println("Error importing data: " + e.getMessage());
        }
    }
}

// TODO: Add command-line argument support to accept CSV file path dynamically
// TODO: Move DB credentials to a config file or environment variable
// TODO: Add user feedback for how many students and choices were inserted
