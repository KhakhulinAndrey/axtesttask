package ru.khakhulin.books.dao;

import org.hsqldb.jdbc.JDBCDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by 1 on 20.09.2017.
 */
public class DBConnection {
    static String dbURL = "jdbc:hsqldb:file:C:/hsql/1/books";
    static String username = "SA";
    static String password = "";

    private static Connection connection;

    public static Connection getConnetion() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dbURL, username, password);
                if (connection != null) {
                    System.out.println("Connected");
                }
                return connection;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        else return connection;

    }
}


