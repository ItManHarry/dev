package com.ch.sys.biz.system.cache.redis;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
/**
 * 	重写Redis序列化类
 * @author 20112004
 *
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();
	static final byte[] EMPTY_ARRAY = new byte[0];
	
	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
	
	@Override
	public byte[] serialize(Object object) {
		if(object == null)
			return EMPTY_ARRAY;
		try {
			return serializer.convert(object);
		}catch(Exception e) {
			return EMPTY_ARRAY;
		}
	}

	@Override
	public Object deserialize(byte[] bytes) {
		if(isEmpty(bytes))
			return null;
		try {
			return deserializer.convert(bytes);
		}catch(Exception e) {
			throw new SerializationException("Can not deserialize", e); 
		}	
	}

}
