package com.demo.controller.sys;

import com.demo.model.sys.User;
import com.demo.service.sys.UserService;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toUserList")
    public ModelAndView toUserList() {
        ModelAndView modelAndView = new ModelAndView("sys/userList");
        return modelAndView;
    }

    @RequestMapping("/getUserList")
    public PageEntity getUserList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit, User user){
        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = userService.findPage(page,limit,user);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取用户列表出错！");
        }
        return pageEntity;
    }


    @RequestMapping("/toUser")
    public ModelAndView toUser(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "isEdit",required = false) String isEdit) {
        ModelAndView model = new ModelAndView("sys/user");
        User user = new User();
        if(StringUtils.isNotEmpty(id)){
            user = userService.getById(id);
        }
        model.addObject("user",user);
        model.addObject("isEdit",isEdit);
        return model;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(User user){
        ModelAndView model = new ModelAndView("sys/userList");
        if(StringUtils.isNotEmpty(user.getId())){
            userService.update(user);
        }else{
            userService.insert(user);
        }
        return model;
    }

    @RequestMapping(value = "/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            userService.delUser(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("删除出错！");
            return result;
        }
    }

    @RequestMapping(value = "/batchDel")
    public Result batchDel(@RequestParam(value = "ids[]")String[] ids){
        Result result = new Result();
        try{
            userService.batchDel(ids);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("批量删除出错！");
            return result;
        }
    }
}
