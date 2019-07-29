package com.ch.sys.biz.system.cache.redis;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@Component
public class RedisFactoryJson {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplateJson;
	/**
	 *	实现命令TTL key,返回给定key的剩余生存时间(TTL Time To Live)
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		return redisTemplateJson.getExpire(key);
	}
	/**
	 * 	实现命令expire,设置过期时间(单位秒)
	 * @param key
	 * @param timeout
	 */
	public void expire(String key, long timeout) {
		redisTemplateJson.expire(key, timeout, TimeUnit.SECONDS);
	}
	/**
	 * 	实现DEL命令,删除一个key
	 * @param key
	 */
	public void del(String key) {
		redisTemplateJson.delete(key);
	}
	/**
	 * 	实现set命令,设置key值
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		redisTemplateJson.opsForValue().set(key, value);
	}
	/**
	 * 	实现set命令,设置key值及超时时间
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value, long timeout) {
		redisTemplateJson.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	/**
	 * 	实现get命令,获取key对应的value
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return redisTemplateJson.opsForValue().get(key);
	}	
}