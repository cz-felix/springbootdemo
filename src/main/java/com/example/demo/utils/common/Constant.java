package com.example.demo.utils.common;

/**
 * Created by chenzhi on 2017/10/12 0012.
 */
public class Constant {

    public static final String[] IGNORE_URI = {"/login","/toLogin"};        //存储不拦截的路径
    public static final String USER_INFO = "loginUser";    //token前缀
    public static final Long SESSION_EXPIRE = 1800L;    //token超时时间
    public static final String COOKIE_TOKEN_KEY = "userCookie";    //传回的cookie的命名

}
