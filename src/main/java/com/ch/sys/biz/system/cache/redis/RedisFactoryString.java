package com.ch.sys.biz.system.cache.redis;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
@Component
public class RedisFactoryString {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public long ttl(String key) {
		return stringRedisTemplate.getExpire(key);
	}
	
	public void expire(String key, long timeout) {
		stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	public long incr(String key, long delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}
	
	public long getIncr(String key, long timeout, int maxnil) {
		Long id = null;
		id = stringRedisTemplate.opsForValue().increment(key, 1);
		if(timeout > 0)
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		if(id > maxnil)
			stringRedisTemplate.delete(key);
		return id;
	}
	
	public Set<String> keys(String pattern){
		return stringRedisTemplate.keys(pattern);
	}
	
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}
	
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	public void set(String key, String value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	public void hset(String key, String field, String value) {
		stringRedisTemplate.opsForHash().put(key, field, value);
	}
	
	public String hget(String key, String field) {
		return (String)stringRedisTemplate.opsForHash().get(key, field);
	}
	
	public void hdel(String key, Object... keys) {
		stringRedisTemplate.opsForHash().delete(key, keys);
	}
	
	public Map<Object, Object> hgetall(String key){
		return stringRedisTemplate.opsForHash().entries(key);
	}
	
	public long lpush(String key, String value) {
		return stringRedisTemplate.opsForList().leftPush(key, value);
	}
	
	public String lpop(String key) {
		return (String)stringRedisTemplate.opsForList().leftPop(key);
	}
	
	public long rpush(String key, String value) {
		return stringRedisTemplate.opsForList().rightPush(key, value);
	}
}