:\Users\Layze\Documents\GitHub\StAlbanDataProjects\F4UniqueSubjects2025\src\main\java\com\form4\App.java
package com.form4;

import com.form4.service.CsvImportService;
import com.form4.util.DatabaseConnection;

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
            System.out.println("Data imported successfully.");
        } catch (Exception e) {
            System.err.println("Error importing data: " + e.getMessage());
        }
    }
}