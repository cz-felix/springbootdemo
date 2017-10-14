package com.example.demo.utils.Interceptor;

import com.example.demo.dao.RedisDao;
import com.example.demo.model.User;
import com.example.demo.utils.common.Constant;
import com.example.demo.utils.common.CookieUtils;
import com.example.demo.utils.common.UserUtils;
import org.activiti.engine.impl.util.json.CookieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;

/**
 * Created by chenzhi on 2017/10/10 0010.
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisDao redisDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //System.out.println("开始preHandle,判断请求是否需要拦截");
        boolean flag = false;
        String servletPath = request.getServletPath();
        //System.out.println("请求路径是: "+servletPath);
        // 检测是否为需要拦截的请求
        for (String s : Constant.IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
            }
        }
        // 拦截处理的请求
        if (!flag) {
            //获取token
            String token = CookieUtils.getCookie(request, Constant.COOKIE_TOKEN_KEY);
            User user = UserUtils.getLoginUser(request);
            if (user == null) {
                // 3、如果查询不到数据。返回用户已经过期。
                // 服务器内部转发，可以带回request
                response.sendRedirect("/toLogin");
            }else{
                // 4、如果查询到数据，说明用户已经登录。
                // 5、需要重置key的过期时间。
                redisDao.expire(Constant.USER_INFO + ":" + token, Constant.SESSION_EXPIRE);
                // 6、把json数据转换成TbUser对象，然后使用e3Result包装并返回。
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>UserInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println(">>>UserInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
