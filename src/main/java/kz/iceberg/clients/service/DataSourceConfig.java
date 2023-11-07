package kz.iceberg.clients.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@Primary
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<HikariDataSource> source = DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .driverClassName("org.postgresql.Driver")
                .username("iceberg")
                .password("bc9a0Gh5oPew")
                .url("jdbc:postgresql://192.168.0.177:5432/crm_clients_service");
        return source.build();
    }
}
