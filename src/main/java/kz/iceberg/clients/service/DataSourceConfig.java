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
        DataSource s = source.build();
//        try {
//
//            System.out.println(s.getConnection().getMetaData().getURL());
//            ResultSet rs = s.getConnection().getMetaData().getCatalogs();
//            ResultSet rs2 = s.getConnection().getMetaData().getSchemas();
//
//            System.out.println(s.getConnection().getMetaData().getUserName());
//
//            System.out.println("#######catalogs#######");
//            int columnCount = rs.getMetaData().getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    Object value = rs.getObject(i);
//                    System.out.print(value + "\t"); // Print each column value
//                }
//                System.out.println(); // Move to the next line for the next row
//            }
//            System.out.println("#######end catalogs#######");
//
//
//            System.out.println("#######schemas#######");
//            columnCount = rs.getMetaData().getColumnCount();
//            while (rs2.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    Object value = rs2.getObject(i);
//                    System.out.print(value + "\t"); // Print each column value
//                }
//                System.out.println(); // Move to the next line for the next row
//            }
//            System.out.println("####### end schemas #######");
//
//
//            Statement statement = s.getConnection().createStatement();
//            String sqlQuery = "SELECT datname FROM pg_database";
//            ResultSet resultSet = statement.executeQuery(sqlQuery);
//
//            while (resultSet.next()) {
//                String databaseName = resultSet.getString("datname");
//                System.out.println(databaseName);
//            }
//        } catch (Error e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return s;
    }
}
