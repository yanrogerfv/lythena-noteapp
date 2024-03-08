package lythena.noteapp.database;

import java.sql.*;

public class PostgreSQLTest {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:postgresql://postgres-db-001-noteapp-test.a.aivencloud.com:19006/defaultdb";
        String username = "avnadmin";
        String password = "AVNS_Uw0KPTMRN89i5NRVF-P";


        // Register the PostgreSQL driver

        try {
            Class.forName("org.postgresql.Driver");


            // Connect to the database

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);


            // Perform desired database operations

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next())
            {
                String columnValue = resultSet.getString("column_name");
                System.out.println("Column Value: " + columnValue);
            }

            // Close the connection

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
