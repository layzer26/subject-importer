package com.form4.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.form4.util.DatabaseConnection;

public class CsvImportService {

    private final String csvFile;
    private final DatabaseConnection dbConnection;

    public CsvImportService(String csvFile, DatabaseConnection dbConnection) {
        this.csvFile = csvFile;
        this.dbConnection = dbConnection;
    }

    public void importData() throws SQLException, IOException {
        try (Connection conn = dbConnection.getConnection(); 
             BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            conn.setAutoCommit(false);
            PreparedStatement insertChoice = conn.prepareStatement(
                    "INSERT INTO subject_choices (student_name, student_id, subject_name, subject_code, choice_number) VALUES (?, ?, ?, ?, ?)");

            // Skip header line
            reader.readLine();
            
            String line;
            int studentCounter = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length < 3) { // Need at least name, form, and one subject
                    continue;
                }

                String studentName = data[0].trim();
                String formClass = data[1].trim(); // This will be part of student_id
                
                // For each subject (starting from index 3)
                for (int i = 3; i < data.length; i++) {
                    String subject = data[i].trim();
                    if (!subject.isEmpty()) {
                        // Create simple numeric student ID
                        String studentId = "S" + String.format("%03d", studentCounter);
                        
                        insertChoice.setString(1, studentName);
                        insertChoice.setString(2, studentId);
                        insertChoice.setString(3, subject); // Subject name
                        insertChoice.setString(4, subject); // Using same value for code (can be updated later)
                        insertChoice.setInt(5, i - 2); // Choice number (3rd column is choice 1)
                        insertChoice.executeUpdate();
                    }
                }
                studentCounter++;
            }

            conn.commit();
            System.out.println("Data imported successfully.");
        }
    }
}
