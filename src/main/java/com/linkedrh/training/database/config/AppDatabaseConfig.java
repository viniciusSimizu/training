package com.linkedrh.training.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.linkedrh.training")
@PropertySource("application.properties")
public class AppDatabaseConfig {

    public static String url;
    public static String user;
    public static String passw;

    @Autowired private Environment env;

    @Bean
    public DataSource postgresDataSource() {
        AppDatabaseConfig.user = this.env.getProperty("db.user");
        AppDatabaseConfig.passw = this.env.getProperty("db.pass");

        String dbName = this.env.getProperty("db.name");
        AppDatabaseConfig.url = "jdbc:postgresql://127.0.0.1:5432/" + dbName;

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(AppDatabaseConfig.user);
        dataSource.setPassword(AppDatabaseConfig.passw);
        dataSource.setUrl(AppDatabaseConfig.url);

        return dataSource;
    }
}
