package com.demo.controller.sys;

import com.demo.dao.sys.UserDao;
import com.demo.model.ActInBean;
import com.demo.model.sys.User;
import com.demo.service.sys.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toUserList")
    public String toUserList() {
        return "sys/userList";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Map<String,Object> getUserList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit, User user){
        PageInfo pageInfo = userService.findPage(page,limit,user);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        return result;
    }

    @RequestMapping("/toUser")
    public String toUser(@RequestParam(value = "id",required = false) String id, Model model) {
        User user = new User();
        if(StringUtils.isNotEmpty(id)){
            user = userService.getById(id);
        }
        model.addAttribute("user",user);
        return "sys/user";
    }

    @RequestMapping(value = "/save")
    public String save(User user){
        if(StringUtils.isNotEmpty(user.getId())){
            userService.update(user);
        }else{
            userService.insert(user);
        }
        return "sys/userList";
    }

    @RequestMapping(value = "/delete")
    public String delete(String id){
        try{
            userService.delUser(id);
            return "sys/userList";
        }catch (Exception e){
            e.printStackTrace();
            return "sys/userList";
        }
    }


}
