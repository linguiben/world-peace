/**
 * @className SQLiteExample
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-08-23 23:34
 */
package com.jupiter.db.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Jupiter.Lin
 * @desc SQLite下载地址：https://www.sqlite.org/download.html
 * @date 2024-08-23 23:34
 */

public class SQLiteExample {
    public static void main(String[] args) {
        // Load the SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Using try-with-resources to manage resources
        String url = "jdbc:sqlite:/Users/jupiter/18.sqlite-tools-linux-x64/db1.db";

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            // Create a table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
            // Insert a sample record
            statement.executeUpdate("INSERT INTO users (name) VALUES ('Jupiter')");
            // Query the database
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                while (resultSet.next()) {
                    System.out.println("User ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
