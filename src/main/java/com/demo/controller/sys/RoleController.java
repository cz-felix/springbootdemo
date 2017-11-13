package com.demo.controller.sys;

import com.demo.model.sys.Resources;
import com.demo.model.sys.Role;
import com.demo.model.sys.RoleResources;
import com.demo.service.sys.RoleResourcesService;
import com.demo.service.sys.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public Map<String,Object> getRoleList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit, Role role){
        PageInfo pageInfo = roleService.findPage(page,limit,role);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        return result;
    }

    @RequestMapping("/rolesWithSelected")
    public List<Role> rolesWithSelected(String userId){
        return roleService.queryRoleListWithSelected(userId);
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public String saveRoleResources(RoleResources roleResources){
        if(StringUtils.isEmpty(roleResources.getRoleId()))
            return "error";
        try {
            roleResourcesService.addRoleResources(roleResources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(Role role) {
        try {
            roleService.add(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(String id){
        try{
            roleService.delRole(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
