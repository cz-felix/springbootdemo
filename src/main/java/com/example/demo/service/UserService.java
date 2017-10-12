package com.example.demo.service;

import com.example.demo.dao.RedisDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.utils.common.Constant;
import com.example.demo.utils.common.Result;
import com.example.demo.utils.common.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by chenzhi on 2017/9/7.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisDao redis;

    @Cacheable(key = "#id" )
    public User findById(String id){
        User user=userDao.findById(id);
        System.out.println("--------------进来说明没有使用缓存-------------------");
        return user;
    }

    public Result getUser(String email, String password){
        User user = userDao.getUser(email,password);
        if(user != null){
            redis.set(user.getId(),user);
            // 2、登录成功后生成token。Token相当于原来的jsessionid，字符串，可以使用uuid。
            String token = UUIDGenerator.uuid();
            // 3、把用户信息保存到redis。Key就是token，value就是TbUser对象转换成json。
            // 4、使用String类型保存Session信息。可以使用“前缀:token”为key
            redis.set(Constant.USER_INFO + ":" + token, user);
            // 5、设置key的过期时间。模拟Session的过期时间。一般半个小时。
            redis.expire(Constant.USER_INFO + ":" + token, Constant.SESSION_EXPIRE);
            // 6、返回e3Result包装token。
            return new Result(Result.RECODE_SUCCESS,null,token);
        }else{
            return new Result(Result.RECODE_ERROR,"用户名或密码错误",null);
        }
    }

    @CacheEvict(key = "#id" )
    public void deleteUser(String id){
        userDao.deleteUser(id);
    }

}
