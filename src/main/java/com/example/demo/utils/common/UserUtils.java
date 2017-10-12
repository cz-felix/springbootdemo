package com.example.demo.utils.common;

import com.example.demo.dao.RedisDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenzhi on 2017/10/12 0012.
 */
@Component
public class UserUtils {

    @Autowired
    private RedisDao redisDao;
    //自身的静态引用
    private static UserUtils userUtils;

    @PostConstruct
    public void init(){
        userUtils = this;
        userUtils.redisDao = this.redisDao;
    }

    public static User getLoginUser(HttpServletRequest request){
        //获取token
        String token = CookieUtils.getCookie(request, Constant.COOKIE_TOKEN_KEY);
        // 2、根据token查询redis。
        Object userObj = userUtils.redisDao.getValueOfObject(Constant.USER_INFO + ":" + token);
        if(userObj != null){
            return (User)userObj;
        }else{
            return null;
        }
    }

    public static void logoutUser(HttpServletRequest request){
        //获取token
        String token = CookieUtils.getCookie(request, Constant.COOKIE_TOKEN_KEY);
        // 根据token删除redis。
        userUtils.redisDao.delete(Constant.USER_INFO + ":" + token);
    }
}
