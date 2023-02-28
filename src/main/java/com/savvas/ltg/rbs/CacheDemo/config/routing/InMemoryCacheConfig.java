/*
 * Copyright 2021, Savvas Learning Company LLC
 *
 * CacheConfig.java
 */
package com.savvas.ltg.rbs.CacheDemo.config.routing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class InMemoryCacheConfig extends CachingConfigurerSupport {

    @Value("${redis.inmemory.hostname}")
    public String hostname;

    @Value("${redis.inmemory.portnumber}")
    public int portnumber;

    @Bean(name = "inMemoryJedisConnectionFactory")
    JedisConnectionFactory inMemoryJedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, portnumber);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(name = "inMemoryRedisTemplate")
    public RedisTemplate<Object, Object> inMemoryRedisTemplate(@Qualifier("inMemoryJedisConnectionFactory")
                                                                   RedisConnectionFactory connectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
        return template;
    }

    @Bean(name = "inMemoryRedisTemplate")
    public RedisTemplate inMemoryRedisTemplate() {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(inMemoryJedisConnectionFactory());
        return template;
    }

    @Bean(name = "inMemoryStringRedisTemplate")
    public StringRedisTemplate inMemoryStringRedisTemplate(@Qualifier("inMemoryJedisConnectionFactory") RedisConnectionFactory cf) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(cf);
        return stringRedisTemplate;
    }

    @Bean(name = "inMemoryCacheConfiguration")
    public RedisCacheConfiguration inMemoryCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}

