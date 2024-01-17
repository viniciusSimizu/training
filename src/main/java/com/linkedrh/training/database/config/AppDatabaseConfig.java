package com.linkedrh.training.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.linkedrh.training")
@PropertySource("application.properties")
public class AppDatabaseConfig {

    public static String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.pass}")
    private String password;

    @Value("${db.name}")
    private String database;

    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(this.user);
        dataSource.setPassword(this.password);

        AppDatabaseConfig.url = "jdbc:postgresql://localhost:5432/" + this.database;
        dataSource.setUrl(AppDatabaseConfig.url);

        return dataSource;
    }
}
