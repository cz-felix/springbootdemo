package com.demo.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.sys.Role;
import com.demo.model.sys.RoleResources;
import com.demo.service.sys.RoleResourcesService;
import com.demo.service.sys.RoleService;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/12 0012.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResourcesService roleResourcesService;

    @RequestMapping("/toRoleList")
    public ModelAndView toRoleList() {
        ModelAndView model = new ModelAndView("sys/roleList");
        return model;
    }

    @RequestMapping("/getRoleList")
    public PageEntity getRoleList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit",defaultValue = "10") Integer limit, Role role){

        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = roleService.findPage(page,limit,role);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取角色列表出错！");
        }
        return pageEntity;
    }

    @RequestMapping("/rolesWithSelected")
    public String rolesWithSelected(String userId){
        PageEntity pageEntity = new PageEntity();
        try {
            List<Role> roles = roleService.queryRoleListWithSelected(userId);
            PageInfo<Role> pageInfo = new PageInfo(roles);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取角色列表出错！");
        }
        String paramsStr = JSONObject.toJSONString(pageEntity);
        return paramsStr;
    }

    @RequestMapping("/toRole")
    public ModelAndView toRole() {
        ModelAndView model = new ModelAndView("sys/role");
        return model;
    }

    @RequestMapping("/update")
    public Result update(Role role){
        Result result = new Result();
        try {
            roleService.update(role);
        }catch (Exception e){
            e.printStackTrace();
            result.setErrMsg("修改角色出错！");
            result.setRetCode(Result.RECODE_ERROR);
        }
        return  result;
    }

    @RequestMapping("/toSelectRoles")
    public ModelAndView toSelectRoles(String userId) {
        ModelAndView model = new ModelAndView("sys/roleSelectList");
        model.addObject("userId",userId);
        return model;
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public Result saveRoleResources(RoleResources roleResources) {
        Result result = new Result();
        if (StringUtils.isEmpty(roleResources.getRoleId())) {
            result.setErrMsg("保存角色权限出错");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        try {
            roleResourcesService.addRoleResources(roleResources);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrMsg("保存角色权限出错");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }

    @RequestMapping(value = "/add")
    public Result add(Role role) {
        Result result = new Result();
        try {
            roleService.add(role);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrMsg("新增角色出错！");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }

    @RequestMapping(value = "/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            roleService.delRole(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setErrMsg("删除角色出错！");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }
}
