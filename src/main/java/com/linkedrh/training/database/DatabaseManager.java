package com.linkedrh.training.database;

import com.linkedrh.training.database.config.AppDatabaseConfig;
import com.linkedrh.training.lib.interfaces.IDatabaseManager;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseManager implements IDatabaseManager {

    public Connection getConnection() throws SQLException {
        final String url = AppDatabaseConfig.url;
        final String user = AppDatabaseConfig.user;
        final String passw = AppDatabaseConfig.passw;

        return DriverManager.getConnection(url, user, passw);
    }
}
