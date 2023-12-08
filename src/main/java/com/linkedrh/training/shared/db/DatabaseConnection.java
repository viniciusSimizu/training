package com.linkedrh.training.shared.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
	String url;
	Properties props = new Properties();

	public DatabaseConnection() {
		this.url =  "jdbc:postgresql://localhost:3000/linkedrh_training";
		this.props.setProperty("user", "root");
		this.props.setProperty("password", "admin123");
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, props);
	}
}
