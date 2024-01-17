package com.linkedrh.training.database;

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
public class AppJdbcConfig {
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
        dataSource.setUrl("jdbc:postgresql://localhost:5432/" + this.database);
        dataSource.setUsername(this.user);
        dataSource.setPassword(this.password);

        return dataSource;
    }
}
