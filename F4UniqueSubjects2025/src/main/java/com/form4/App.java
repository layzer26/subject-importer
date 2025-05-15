package com.form4;

import java.io.IOException;
import java.sql.SQLException;

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
        } catch (SQLException | IOException e) {
            System.err.println("Error importing data: " + e.getMessage());
        }
    }
}