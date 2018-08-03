package com.tutorial.psqlpsqldemo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String psl_url;
    @Value("${spring.datasource.username}")
    private String psl_username;
    @Value("${spring.datasource.password}")
    private String psl_password;
    @Value("${spring.datasource.driverClassName}")
    private String psl_driver;


    @Bean
    @Qualifier("database")
    public DataSource MyDataSource() throws Exception {
        DataSourceBuilder dataSourceBuilder_psl = DataSourceBuilder.create();
        dataSourceBuilder_psl.driverClassName(psl_driver);
        dataSourceBuilder_psl.username(psl_username);
        dataSourceBuilder_psl.password(psl_password);
        dataSourceBuilder_psl.url(psl_url);
        return dataSourceBuilder_psl.build();

    }
}
