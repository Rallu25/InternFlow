package com.ibm.internflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("admin");
        dataSource.setPassword("password");
        dataSource.setSchema("intern_flow");
        dataSource.setUrl(
                "jdbc:postgresql://localhost:5432/postgres");


        return dataSource;
    }
}
