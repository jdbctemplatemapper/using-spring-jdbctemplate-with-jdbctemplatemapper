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

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource sqlDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public JdbcTemplateMapper jdbcTemplateMapper(JdbcTemplate jdbcTemplate) {
    return  new JdbcTemplateMapper(jdbcTemplate);
  }

}
