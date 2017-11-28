package com.demo.controller;

import com.demo.model.sys.User;
import com.demo.utils.common.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenzhi on 2017/10/9 0009.
 */
@RestController
public class LoginController{

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(HttpServletRequest request, User user){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPwd())) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("用户名或密码不能为空！");
            return result;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPwd());
        try {
            subject.login(token);
        }catch (LockedAccountException lae) {
            token.clear();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("用户已经被锁定不能登录，请与管理员联系！");
            return result;
        } catch (AuthenticationException e) {
            token.clear();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("用户或密码不正确！");
            return result;
        }
        return result;
    }

    @RequestMapping(value={"/main",""})
    public ModelAndView index(HttpServletRequest request){
        ModelAndView model = new ModelAndView("main");
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        model.addObject("user",user);
        return model;
    }

    @RequestMapping("/403")
    public ModelAndView forbidden(){
        ModelAndView model = new ModelAndView("403");
        return model;
    }

}
