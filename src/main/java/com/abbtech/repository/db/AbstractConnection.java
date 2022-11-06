package com.abbtech.repository.db;

import com.abbtech.heroku.HerokuEnv;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractConnection {

    public Connection localConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db_tinder";
        String username = "root";
        String password = "12345qwertQWERT";
        return DriverManager.getConnection(url, username, password);
    }

    public Connection connection() throws Exception {
        String url = HerokuEnv.jdbc_url();
        String user = HerokuEnv.jdbc_username();
        String pass = HerokuEnv.jdbc_password();
        return DriverManager.getConnection(url, user, pass);
    }

}