package ru.khakhulin.books.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 1 on 20.09.2017.
 */
public class DBConnection {
    static String dbURL = "jdbc:postgresql://localhost:5432/books";
    static String username = "user";
    static String password = "password";

    private static Connection connection;

    public static Connection getConnetion() {

        if (connection == null) {
            try {
                Connection conn = DriverManager.getConnection(dbURL, username, password);
                if (conn != null) {
                    System.out.println("Connected");
                }
                return conn;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        else return connection;

    }
}


