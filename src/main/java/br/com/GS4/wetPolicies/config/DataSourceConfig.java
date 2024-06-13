package br.com.GS4.wetPolicies.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource getDataSource() {
        logger.info("Configuring DataSource with URL: {}", dbUrl);
        // For security reasons, avoid logging the password directly
        return DataSourceBuilder.create()
                .url(dbUrl)
                .build();
    }
}
