package com.demo.controller.sys;

import com.demo.model.sys.Resources;
import com.demo.service.sys.ResourcesService;
import com.demo.utils.shiro.ShiroService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/12 0012.
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/toResourcesList")
    public ModelAndView toResourcesList() {
        ModelAndView model = new ModelAndView("sys/resourcesList");
        return model;
    }

    @RequestMapping("/getResourcesList")
    public Map<String,Object> getResourcesList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit, Resources resources){
        PageInfo pageInfo = resourcesService.findPage(page,limit,resources);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        return result;
    }

    @RequestMapping("/resourcesWithSelected")
    public List<Resources> resourcesWithSelected(String roleId){
        return resourcesService.queryResourcesListWithSelected(roleId);
    }

    @RequestMapping("/loadMenu")
    public List<Resources> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type","1");
        map.put("userid",userid);
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    @CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/add")
    public String add(Resources resources){
        try{
            resourcesService.save(resources);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/delete")
    public String delete(String id){
        try{
            resourcesService.delete(id);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

}
