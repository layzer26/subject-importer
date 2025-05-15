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
        try (Connection conn = dbConnection.getConnection(); BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            conn.setAutoCommit(false);
            PreparedStatement insertStudent = conn.prepareStatement(
                    "INSERT INTO students (student_name, form) VALUES (?, ?)");

            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length < 2) {
                    continue;
                }

                String name = data[0].trim();
                String formText = data[1].trim();

                if (name.isEmpty() || formText.isEmpty()) {
                    continue;
                }

                insertStudent.setString(1, name);
                insertStudent.setInt(2, Integer.parseInt(formText));
                insertStudent.executeUpdate();
            }

            conn.commit();
        }
    }
}
