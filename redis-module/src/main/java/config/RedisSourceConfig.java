package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisSourceConfig {
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisPooled resourcePool() {
		return new JedisPooled();
	}
}
