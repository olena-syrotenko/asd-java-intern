package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;

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
		return jedis.get(key);
	}

	public void saveValueByKey(String key, String value) {
		jedis.set(key, value);
	}

	public void saveList(String keyList, List<String> list) {
		jedis.rpush(keyList, list.toArray(String[]::new));
	}

	public void saveElementIntoList(String keyList, String value) {
		jedis.rpush(keyList, value);
	}

	public List<String> retrieveList(String keyList) {
		return jedis.lrange(keyList, 0, -1);
	}

	public void saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		jedis.hset(primaryKey, secondaryKey, value);
	}

	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		return jedis.hget(primaryKey, secondaryKey);
	}

	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		return jedis.hgetAll(primaryKey);
	}

	public void saveValueWithExpireDate(String key, String value, long expireDate) {
		jedis.setex(key, expireDate, value);
	}
}
