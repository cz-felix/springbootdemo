package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.ActivitiUtil;
import com.example.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenzhi on 2017/9/7.
 */
@RestController
@RequestMapping("/test")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ActivitiUtil activitiUtil;

    @RequestMapping("/getUserInfo")
    public ModelAndView getUserInfo() {
       User user = userService.findById("1");
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
           /* activitiUtil.model_create();*/
           /* activitiUtil.contextLoads();*/
        }
        ModelAndView model = new ModelAndView("index");
        model.addObject("index","1111");
        return model;
    }

    @RequestMapping("/updateRedis")
    public String updateRedis() {
        userService.deleteUser("1");
        return "";
    }

}
