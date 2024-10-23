package br.com.aab.payrolljob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/batch_db")
                .username("root")
                .password("aabPwd")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
