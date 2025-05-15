package com.form4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class App {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/f4subjectchoices";
        String dbUser = "root";
        String dbPassword = "Blue@1963"; 

        String csvFile = "src/main/resources/data/F4_subject_choices.csv";

        try (
                Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword); BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            conn.setAutoCommit(false); // Group inserts into a transaction

            PreparedStatement insertStudent = conn.prepareStatement(
                    "INSERT INTO students (student_name, form) VALUES (?, ?)");

            reader.readLine(); // Skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1); // keep empty strings

                String name = data[0].trim(); // Pupil
                String formText = data[1].trim(); // Subjects (actually the form)

                if (name.isEmpty() || formText.isEmpty()) {
                    continue;
                }

                int form = Integer.parseInt(formText);

                insertStudent.setString(1, name);
                insertStudent.setInt(2, form);
                insertStudent.executeUpdate();
            }

            conn.commit();
            System.out.println("Students inserted successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}