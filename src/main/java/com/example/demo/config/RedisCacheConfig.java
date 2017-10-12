package com.example.demo.config;

import java.lang.reflect.Method;
import java.net.UnknownHostException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by chenzhi on 2017/10/9 缓存配置类
 * 这里实现CachingConfigurerSupport主要是方便使用自定义keyGenerator
 */
@Configuration
@EnableCaching // 启用缓存
public class RedisCacheConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int mainIdle;

    /**
     * 配置JedisPoolConfig
     *
     * @return JedisPoolConfig实体
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMaxWaitMillis(this.maxWait);
        jedisPoolConfig.setMinIdle(this.mainIdle);
        return jedisPoolConfig;
    }

    /**
     * 配置JedisPool
     *
     * @return
     */
    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = null;
        if (this.password != null) {
            jedisPool = new JedisPool(this.jedisPoolConfig(), this.host, this.port, this.timeout, this.password);
        } else {
            jedisPool = new JedisPool(this.jedisPoolConfig(), this.host, this.port);
        }
        return jedisPool;
    }

    /**
     * RedisConnectionFactory配置
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(this.jedisPoolConfig());
    }

    /**
     * 配置StringRedisTemplate实体
     *
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(this.redisConnectionFactory());
        return stringRedisTemplate;
    }

    /**
     * 配置RedisTemplate
     *
     * @return 返回redisTemplate实体
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(this.redisConnectionFactory());
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //使用StringRedisSerializer作为序列化key，默认是使用JdkSerializationRedisSerializer，会出现脏字段在key中
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 自定义keyGenerator
     *
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append("mykey" + ":");
                sb.append(method.getName() + ":");
                for (Object object : params) {
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 配置redis缓存管理对象
     *
     * @param redisTemplate
     * @return
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        return new RedisCacheManager(redisTemplate);

    }
}