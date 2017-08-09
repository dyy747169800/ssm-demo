package com.uban.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheClient {
	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;

	public <T> T hget(String key, String field, Class<T> clazz) {
		String text = hget(key, field);
		T result = JSON.parseObject(text, clazz);
		return result;
	}

	public String hget(String key, String field) {
		String text = (String) redisTemplate.opsForHash().get(key, field);
		return text;
	}

	public Map<Object, Object> hentries(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public void hset(String key, String field, String o) {
		redisTemplate.opsForHash().put(key, field, o);
	}

	public void set(String key, Object value, long expire) {
		redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expire, TimeUnit.SECONDS);
	}

	public void set(String key, String value, long expire) {
		redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void setJson(String key, Object o) {
		redisTemplate.opsForValue().set(key, JSON.toJSONString(o));
	}

	public <T> T get(String key, Class<T> clazz) {
		String text = (String) redisTemplate.opsForValue().get(key);
		if (text == null) {
			return null;
		}
		T result = JSON.parseObject(text, clazz);
		return result;
	}

	public <T> T getJson(String key, Class<T> clazz) {
		String text = (String) redisTemplate.opsForValue().get(key);
		if (text == null) {
			return null;
		}
		T result = JSON.parseObject(text, clazz);
		return result;
	}

	public void setMap(String key, Map<String, Object> map) {
		redisTemplate.opsForValue().set(key, JSON.toJSONString(map));
	}

	public String get(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}

	/**
	 * key有效的时间
	 * 
	 * @param key
	 * @return
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	public void setExpire(String key, long expire) {
		redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	public Map<String, Object> getMap(String key) {
		String text = (String) redisTemplate.opsForValue().get(key);
		if (text == null) {
			return null;
		}
		Map<String, Object> map = JSON.parseObject(text);
		return map;
	}

	public void hset(String key, String field, Object o, long expire) {
		redisTemplate.opsForHash().put(key, field, JSON.toJSONString(o));
		redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	public void hsetToJsonString(String key, String field, String o, long expire) {
		redisTemplate.opsForHash().put(key, field, o);
		redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	public void hsetToJsonString(String key, String field, String o) {
		redisTemplate.opsForHash().put(key, field, o);
	}

	public String hgetToJsonString(String key, String field) {
		String text = (String) redisTemplate.opsForHash().get(key, field);
		return text;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public void delete(Set<String> keys) {
		this.redisTemplate.delete(keys);
	}

	public void delete(String key) {
		this.redisTemplate.delete(key);
	}

	public void delete(String key, String hashKeys) {
		this.redisTemplate.opsForHash().delete(key, hashKeys);
	}

	public boolean hasKey(String key) {
		return this.redisTemplate.hasKey(key);
	}

	public boolean hasKey(String key, String hashKey) {
		return this.redisTemplate.opsForHash().hasKey(key, hashKey);
	}
}
