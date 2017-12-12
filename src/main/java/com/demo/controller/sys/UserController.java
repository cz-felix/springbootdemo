package com.demo.controller.sys;

import com.demo.model.sys.User;
import com.demo.model.sys.UserRole;
import com.demo.service.sys.UserRoleService;
import com.demo.service.sys.UserService;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.PasswordHelper;
import com.demo.utils.common.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
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
    @Autowired
    private UserRoleService userRoleService;

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
        if("1".equals(id)){
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("系统内置管理员不可删除！！");
            return result;
        }
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
        if(ArrayUtils.contains(ids,"1")){
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("系统内置管理员不可删除！！");
            return result;
        }
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

    /**
     * 保存用户角色
     * @param userRole 用户角色
     *  	  此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public Result saveUserRoles(UserRole userRole){
        Result result = new Result();
        if(org.springframework.util.StringUtils.isEmpty(userRole.getUserId())) {
            result.setErrMsg("保存用户角色出错");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        try {
            userRoleService.addUserRole(userRole);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrMsg("保存用户角色出错");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }

    /**
     * 跳转至修改密码页面
     * @return
     */
    @RequestMapping("/toUpdatePassword")
    public ModelAndView toUpdatePassword(String userId){
        ModelAndView model = new ModelAndView("sys/userUpdatePassword");
        model.addObject("userId",userId);
        return  model;
    }

    /**
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/updatePassword")
    public Result updatePassword(String id, String oldPassword, String newPassword){
        Result result = new Result();
        try {
            User user = userService.getById(id);
            PasswordHelper passwordHelper = new PasswordHelper();
            oldPassword = passwordHelper.encryptPassword(user,oldPassword);
            if(oldPassword.equals(user.getPwd())){
                user.setPwd(newPassword);
                passwordHelper.encryptPassword(user);
                userService.updatePassword(user);
            }else{
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("旧密码不正确！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("修改密码出错！");
        }
        return  result;
    }
}
