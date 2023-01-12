package team.asd.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPooled;

@Configuration
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Setter
public class RedisResourcePool {
	private String host;
	private Integer port;
	private String password;

	@Bean
	public JedisPooled resourcePool() {
		return new JedisPooled(host, port, null, password);
	}
}
