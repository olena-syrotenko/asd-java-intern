package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;
import team.asd.exceptions.RedisValidationException;

import java.util.List;
import java.util.Map;

@Service
public class RedisClient {
	private final JedisPooled jedis;

	@Autowired
	public RedisClient(JedisPooled jedis) {
		this.jedis = jedis;
	}

	public String readByKey(String key) {
		if (StringUtils.isBlank(key)) {
			throw new RedisValidationException("Wrong key was provided");
		}
		return jedis.get(key);
	}

	public void saveValueByKey(String key, String value) {
		if (StringUtils.isAnyBlank(key, value)) {
			throw new RedisValidationException("Wrong parameters were provided");
		}
		jedis.set(key, value);
	}

	public void saveList(String keyList, List<String> list) {
		if (StringUtils.isBlank(keyList) || CollectionUtils.isEmpty(list)) {
			throw new RedisValidationException("Wrong parameters were provided");
		}
		jedis.rpush(keyList, list.toArray(String[]::new));
	}

	public void saveElementIntoList(String keyList, String value) {
		if (StringUtils.isAnyBlank(keyList, value)) {
			throw new RedisValidationException("Wrong parameters were provided");
		}
		jedis.rpush(keyList, value);
	}

	public List<String> retrieveList(String keyList) {
		if (StringUtils.isBlank(keyList)) {
			throw new RedisValidationException("Wrong key was provided");
		}
		return jedis.lrange(keyList, 0, -1);
	}

	public void saveValueInHashMap(String primaryKey, String secondaryKey, String value, Long expireDate) {
		if (StringUtils.isAnyBlank(primaryKey, secondaryKey, value)) {
			throw new RedisValidationException("Wrong parameters were provided");
		}
		jedis.hset(primaryKey, secondaryKey, value);
		if (expireDate != null) {
			jedis.expire(primaryKey, expireDate);
		}
	}

	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		if (StringUtils.isAnyBlank(primaryKey, secondaryKey)) {
			throw new RedisValidationException("Wrong keys were provided");
		}
		return jedis.hget(primaryKey, secondaryKey);
	}

	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		if (StringUtils.isBlank(primaryKey)) {
			throw new RedisValidationException("Wrong key was provided");
		}
		return jedis.hgetAll(primaryKey);
	}

	public void saveValueWithExpireDate(String key, String value, long expireDate) {
		if (StringUtils.isAnyBlank(key, value) || expireDate <= 0) {
			throw new RedisValidationException("Wrong parameters were provided");
		}
		jedis.setex(key, expireDate, value);
	}
}
