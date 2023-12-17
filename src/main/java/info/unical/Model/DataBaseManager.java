package info.unical.Model;

import java.sql.*;

public class DataBaseManager
{
    static String connectionString = "jdbc:sqlite:Database.db" ;
    private static Connection connection;
    //per stabilire una connessione al db:

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw new RuntimeException(e);

        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to database closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error with database closure.");
            e.printStackTrace(); //fa risalire agli errori
        }
    }

}
