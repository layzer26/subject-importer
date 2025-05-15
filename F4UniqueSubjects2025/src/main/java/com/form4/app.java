package com.form4;

import java.io.*;
import java.sql.*;

public class App {

    public static void main(String[] args) {
        String jdbURL = "jdbc:mysql://localhost:3306/unique_subjects";
        String dbUser = "root";
        String dbPassword = "Blue@1963";

        String csvFile = "C:\\Users\\Layze\\Documents\\GitHub\\StAlbanDataProjects\\F4UniqueSubjects2025\\src\\data\\2025.csv";

        try (
                Connection conn = DriverManager.getConnection(jdbURL, dbUser, dbPassword); 
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));) 
                {
                    conn.setAutoCommit(false); //Grouping insets into a transaction

                    PreparedStatement insertStudent = conn.prepareStatement("INSERT INTO unique_subjects (subject_name, subject_code) VALUES (?, ?)");

        } catch (Exception e) {
        }
    }
}
