package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.common.Constant;
import com.example.demo.utils.common.CookieUtils;
import com.example.demo.utils.common.Result;
import com.example.demo.utils.common.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenzhi on 2017/10/9 0009.
 */
@RestController
public class LoginController{
    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public ModelAndView toLogin() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response, User user){
        Result result = userService.getUser(user.getEmail(),user.getPassword());
        if(result.getRetCode() == Result.RECODE_SUCCESS){
            String token = result.getData().toString();
            //设置cookie的key和value，key随便字符串，value为token值
            CookieUtils.setCookie(response, Constant.COOKIE_TOKEN_KEY, token);
        }
        return result;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        UserUtils.logoutUser(request);
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping("/main")
    public ModelAndView index(HttpServletRequest request){
        User user = UserUtils.getLoginUser(request);
        ModelAndView model = new ModelAndView("main");
        model.addObject("user",user);
        return model;
    }

}
