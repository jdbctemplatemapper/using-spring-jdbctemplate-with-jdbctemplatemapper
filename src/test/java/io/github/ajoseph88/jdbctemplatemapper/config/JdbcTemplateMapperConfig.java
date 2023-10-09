package io.github.ajoseph88.jdbctemplatemapper.config;


import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import io.github.jdbctemplatemapper.core.JdbcTemplateMapper;

@Component
public class JdbcTemplateMapperConfig {

  // see application.properties for spring.datasource configuration
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource sqlDataSource() {
    return DataSourceBuilder.create().build();
  }

  // Once Spring sees that a DataSource bean is configured it instantiates a default JdbcTemplate
  // bean. Use the JdbcTemplate bean to configure JdbcTemplateMapper
  @Bean
  public JdbcTemplateMapper jdbcTemplateMapper(JdbcTemplate jdbcTemplate) {
    return new JdbcTemplateMapper(jdbcTemplate, null, "tutorial");
    
    // for Postgres, Oracle, SqlServer need to pass in  schema name as argument.
    // return new JdbcTemplateMapper(jdbcTemplate, THE_SCHEMA_NAME);
  }

}
