package com.abbtech.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractConnection {

    public Connection connection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/tinder_db";
        String username = "root";
        String password = "12345qwertQWERT";
        return DriverManager.getConnection(url, username, password);
    }

}
