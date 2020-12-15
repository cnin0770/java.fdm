package com.fdm.w8.jdbc;

import org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionPool {
    private static final String URL = "jdbc:h2:file:./db/app";
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpswd";

    Connection getConnection() {
        Driver driver = new Driver();
        try {
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void release(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
