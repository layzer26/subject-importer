package com.form4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class
 * 
 * TODO: Implement connection pooling for better performance
 * TODO: Add retry mechanism for failed connections
 * TODO: Add connection timeout settings
 * TODO: Add proper connection cleanup
 * TODO: Consider using a proper connection pool library (e.g., HikariCP)
 */
public class DatabaseConnection {

    private final String jdbcURL;
    private final String username;
    private final String password;

    public DatabaseConnection(String jdbcURL, String username, String password) {
        this.jdbcURL = jdbcURL;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, username, password);
    }
}
