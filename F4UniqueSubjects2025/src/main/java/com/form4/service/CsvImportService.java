package com.form4.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.form4.util.DatabaseConnection;

public class CsvImportService {

    private final String csvFile;
    private final DatabaseConnection dbConnection;
    private int totalStudents = 0;
    private int totalChoices = 0;

    public CsvImportService(String csvFile, DatabaseConnection dbConnection) {
        this.csvFile = csvFile;
        this.dbConnection = dbConnection;
    }

    public void importData() throws SQLException, IOException {
        try (Connection conn = dbConnection.getConnection();
             Scanner scanner = new Scanner(new File(csvFile))) {
            
            conn.setAutoCommit(false);
            PreparedStatement insertChoice = conn.prepareStatement(
                    "INSERT INTO subject_choices (student_name, student_id, subject_name, subject_code, choice_number) VALUES (?, ?, ?, ?, ?)");

            // Skip header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            int studentCounter = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Use Scanner to parse each line
                try (Scanner lineScanner = new Scanner(line).useDelimiter(",")) {
                    if (!lineScanner.hasNext()) {
                        continue;
                    }

                    String studentName = lineScanner.next().trim();
                    String formClass = lineScanner.next().trim();

                    // Skip the third column (usually contains a number)
                    if (lineScanner.hasNext()) {
                        lineScanner.next();
                    }

                    // Process subject choices
                    int choiceNumber = 1;
                    while (lineScanner.hasNext()) {
                        String subject = lineScanner.next().trim();
                        if (!subject.isEmpty()) {
                            String studentId = "S" + String.format("%03d", studentCounter);
                            
                            insertChoice.setString(1, studentName);
                            insertChoice.setString(2, studentId);
                            insertChoice.setString(3, subject);
                            insertChoice.setString(4, subject);
                            insertChoice.setInt(5, choiceNumber);
                            insertChoice.executeUpdate();
                            
                            totalChoices++;
                        }
                        choiceNumber++;
                    }
                    totalStudents++;
                }
                studentCounter++;
            }

            conn.commit();
            System.out.printf("Import completed successfully. Processed %d students with %d subject choices.%n", 
                            totalStudents, totalChoices);
        }
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public int getTotalChoices() {
        return totalChoices;
    }
}
// TODO: Validate that subject names are from an approved list (optional whitelist check)
// TODO: Insert subject names into a `subjects` table if not already present (normalize schema)
// TODO: Replace placeholder subject code with actual code mapping logic (e.g., "Mat" → "MAT001")
// TODO: Check for and prevent duplicate student entries (based on name + form)
// TODO: Add input validation for subject choices (e.g., maximum number of choices)
// TODO: Add progress reporting for large files
// TODO: Consider using CSV parsing library for more robust handling
