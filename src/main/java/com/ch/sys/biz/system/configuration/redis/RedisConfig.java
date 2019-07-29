package com.ch.sys.biz.system.configuration.redis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.ch.sys.biz.system.cache.redis.RedisObjectSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Redis Configuration
 * @author 20112004
 *
 */
@Configuration
public class RedisConfig {
	/**
	 * byte格式序列化
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplateBytes(RedisConnectionFactory factory){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		//开启事务
		template.setEnableTransactionSupport(true);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	/**
	 * json格式序列化
	 * 	此处将redis使用的默认序列化JDKSerializer更改为了Spring的Serializer
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplateJson(RedisConnectionFactory factory){
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}	
}