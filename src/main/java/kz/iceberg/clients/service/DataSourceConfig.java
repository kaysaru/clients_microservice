package kz.iceberg.clients.service;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
//@Primary
//public class DataSourceConfig {
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder<PGSimpleDataSource> source = DataSourceBuilder
//                .create()
//                .type(PGSimpleDataSource.class)
//                .driverClassName("org.postgresql.Driver")
//                .username("postgres")
//                .password("dima3raza")
//                .url("jdbc:postgresql://192.168.0.177:5432/");
//        return source.build();
//    }
//}
