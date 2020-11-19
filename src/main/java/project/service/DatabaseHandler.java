package project.service;

import java.sql.*;

public class DatabaseHandler {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5433/kpo";
    static final String USER = "postgres";
    static final String PASS = "root";

    public static Connection getDbConnection() {

        Connection dbConnection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC not found:(");
            e.printStackTrace();
        }

        try {

            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("DB not found(((");
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public static void main(String[] args) {
        System.out.println(DatabaseHandler.getDbConnection());
    }
}