/*package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/donation_system";  // Update with your DB URL
    private static final String USER = "root";  // Update with your DB username
    private static final String PASSWORD = "2266065pg$";  // Update with your DB password

    private static Connection connection;

    // Singleton instance to get the database connection
    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    // Static method to get the connection instance
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Unable to connect to the database.", e);
            }
        }
        return connection;
    }

    // If you really want to use getInstance method, you could add it as a wrapper for getConnection()
    public static DatabaseConnection getInstance() {
        return new DatabaseConnection();  // This would still allow you to get the connection
    }
}*/

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/donation_system";
    private static final String USER = "root"; // Change as needed
    private static final String PASSWORD = "Namith12345@"; // Change as needed

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

