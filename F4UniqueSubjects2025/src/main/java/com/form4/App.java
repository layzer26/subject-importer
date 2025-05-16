package com.form4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

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
        if (args.length < 1) {
            System.out.println("Usage: java -jar app.jar <path-to-csv-file>");
            System.out.println("Example: java -jar app.jar data.csv");
            return;
        }

        String csvFile = args[0];
        String jdbcURL = "jdbc:mysql://localhost:3306/";
        String dbUser = "root";
        String dbPassword = "";

        try {
            // First, create the database and table
            createDatabase(jdbcURL, dbUser, dbPassword);
            
            // Then import the CSV data
            importCsvData(csvFile, jdbcURL + "csv_import", dbUser, dbPassword);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createDatabase(String jdbcURL, String user, String password) throws Exception {
        // Read the SQL script
        try (InputStream in = App.class.getResourceAsStream("/sql/create_database.sql")) {
            if (in == null) {
                throw new IOException("Could not find create_database.sql");
            }

            String sql = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            
            // Execute the SQL script
            try (Connection conn = DriverManager.getConnection(jdbcURL, user, password)) {
                Statement stmt = conn.createStatement();
                
                // Split the script into individual commands
                for (String command : sql.split(";")) {
                    if (!command.trim().isEmpty()) {
                        stmt.execute(command);
                    }
                }
                System.out.println("Database and table created successfully");
            }
        }
    }

    private static void importCsvData(String csvFile, String dbUrl, String user, String password) throws Exception {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            
            conn.setAutoCommit(false);
            String insertSQL = "INSERT INTO csv_data (row_number, column_name, column_value) VALUES (?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                String line;
                String[] headers = null;
                int rowNumber = 0;
                
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",", -1); // -1 to keep empty values
                    
                    if (headers == null) {
                        headers = values;
                        System.out.println("Found columns: " + Arrays.toString(headers));
                        continue;
                    }
                    
                    rowNumber++;
                    for (int i = 0; i < values.length && i < headers.length; i++) {
                        pstmt.setInt(1, rowNumber);
                        pstmt.setString(2, headers[i]);
                        pstmt.setString(3, values[i].trim());
                        pstmt.executeUpdate();
                    }
                    
                    if (rowNumber % 1000 == 0) {
                        conn.commit();
                        System.out.println("Processed " + rowNumber + " rows");
                    }
                }
                
                conn.commit();
                System.out.println("CSV import completed. Total rows processed: " + rowNumber);
            }
        }
    }
}

// TODO: Add command-line argument support to accept CSV file path dynamically
// TODO: Move DB credentials to a config file or environment variable
// TODO: Add user feedback for how many students and choices were inserted
