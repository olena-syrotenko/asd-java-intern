package team.asd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("demo")
public class DataSourceConfigDemo {

	@Bean
	@ConfigurationProperties(prefix = "demo.spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.sqlite.JDBC")
				.build();
	}
}
