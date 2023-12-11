package com.linkedrh.training.shared.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    @Autowired
    private Environment environment;

    public Connection getConnection() throws SQLException {
        String url = environment.getProperty("spring.datasource.url");
        String user = environment.getProperty("spring.datasource.user");
        String passwd = environment.getProperty("spring.datasource.passwd");

        return DriverManager.getConnection(url, user, passwd);
    }
}

