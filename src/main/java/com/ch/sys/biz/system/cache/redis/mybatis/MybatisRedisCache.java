package com.ch.sys.biz.system.cache.redis.mybatis;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import com.ch.sys.biz.system.utils.SpringContextUtils;
/**
 * 	自定义实现Mybatis的Cache
 * @author 20112004
 *
 */
public class MybatisRedisCache implements Cache {

	private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private String id;
	
	private void getRedisTemplate() {
		if(redisTemplate == null)
			redisTemplate = (RedisTemplate<String, Object>)SpringContextUtils.getBean("redisTemplateJson");
	}
	
	public MybatisRedisCache() {
		
	}
	
	public MybatisRedisCache(final String id) {
		if(id == null)
			throw new IllegalArgumentException("Mybatis Cache Instance requires an ID");
		logger.info("Mybatis Cache ID is : " + id);
		this.id = id;				
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		if(value != null)
			this.getRedisTemplate();
		redisTemplate.opsForValue().set(key.toString(), value, 1, TimeUnit.DAYS);
	}

	@Override
	public Object getObject(Object key) {
		try {
			if(key != null) {
				this.getRedisTemplate();
				Object o = redisTemplate.opsForValue().get(key.toString());
				return o;
			}
		}catch(Exception e) {
			logger.error("Redis getObject of Mybatis");
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		try {
			if(key != null) {
				this.getRedisTemplate();
				redisTemplate.delete(key.toString());
			}
		}catch(Exception e) {
			logger.error("Redis removeObject of Mybatis");
		}
		return null;
	}

	@Override
	public void clear() {
		System.out.println("--------------do clear the mybatis cache------------------");
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled())
			logger.debug("Clear Redis Cache of Mybatis");
		try {
			this.getRedisTemplate();	
			//使用*通配符获取所有相关key
			Set<String> keys = redisTemplate.keys("*"+this.id+"*");
			if(keys != null && keys.size() != 0) {
				redisTemplate.delete(keys);
			}else {
				System.out.println("-------------------------cache clear keys is empty...-------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getSize() {
		this.getRedisTemplate();
		
		Long size = (Long)redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				return connection.dbSize();
			}
			
		});
		return size.intValue();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLock;
	}

}