package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by chenzhi on 2017/10/9 0009.
 */
@Component
public class RedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//  @Resource(name = "stringRedisTemplate")
//  private ValueOperations<String, String> valOpsStr;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//  @Resource
//  private ValueOperations<String, Object> valOps;
    /**
     * 存储字符串
     * @param key string类型的key
     * @param value String类型的value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储对象
     * @param key String类型的key
     * @param value Object类型的value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * 根据key获取字符串数据
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public Object getValueOfObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 根据key删除缓存信息
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据key设置超时时间
     * @param key
     * @param seconds
     */
    public void expire(String key,Long seconds){
        redisTemplate.expire(key,seconds, TimeUnit.SECONDS);
    }
}
