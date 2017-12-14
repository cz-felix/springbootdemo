package com.demo.controller.sys;

import com.demo.model.sys.Resources;
import com.demo.service.sys.ResourcesService;
import com.demo.utils.common.Constant;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.Result;
import com.demo.utils.shiro.ShiroService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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

    @RequestMapping("/toResourcesTree")
    public ModelAndView toResourcesTree() {
        ModelAndView model = new ModelAndView("sys/resourcesTree");
        List<Resources> resourcesList = resourcesService.queryAll();
        model.addObject("resourcesList",resourcesList);
        return model;
    }

    @RequestMapping("/getResourcesList")
    public PageEntity getResourcesList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit, Resources resources){
        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = resourcesService.findPage(page, limit, resources);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取资源列表出错！");
        }
        return pageEntity;
    }

    @RequestMapping("/toSelResourcesTree")
    public ModelAndView toSelResourcesTree(String roleId) {
        ModelAndView model = new ModelAndView("sys/resourcesSelList");
        model.addObject("roleId",roleId);
        return model;
    }

    @RequestMapping("/resourcesWithSelected")
    public List<Map<String,Object>> resourcesWithSelected(String roleId){
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        List<Resources> resourcesList = resourcesService.queryResourcesListWithSelected(roleId);
        for (Resources resources : resourcesList) {
            if(Constant.PARENT_NODE.equals(resources.getParentId())){
                Map<String,Object> map = new HashMap<String,Object>();
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                map.put("title",resources.getName());
                map.put("value",resources.getId());
                map.put("checked",resources.getChecked());
                map.put("disabled","false");
                this.recursionResources(resources.getId(),resourcesList,list);
                map.put("data",list);
                result.add(map);
            }
        }
        return  result;
    }

    /**
     * 递归获取资源树
     * @param parentId
     * @param resourcesList
     * @param returnList
     */
    private void recursionResources(String parentId,List<Resources> resourcesList,List<Map<String,Object>> returnList){
        if(resourcesList != null && resourcesList.size() > 0){
            for (Resources resources : resourcesList) {
                if(parentId.equals(resources.getParentId())){
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("title",resources.getName());
                    map.put("value",resources.getId());
                    map.put("checked",resources.getChecked());
                    map.put("disabled","false");
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    this.recursionResources(resources.getId(),resourcesList,list);
                    map.put("data",list);
                    returnList.add(map);
                }
            }
        }
    }

    @RequestMapping("/loadMenu")
    public List<Resources> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        String userId = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type","1");
        map.put("userId",userId);
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    @RequestMapping("/toResources")
    public ModelAndView toResources(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "isEdit",required = false) String isEdit) {
        ModelAndView model = new ModelAndView("sys/resources");
        Resources resources = new Resources();
        if(StringUtils.isNotEmpty(id)){
            resources = resourcesService.get(id);
        }
        model.addObject("resources",resources);
        model.addObject("isEdit",isEdit);
        return model;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(Resources resources){
        ModelAndView model = new ModelAndView("sys/resourcesList");
        try {
            if(StringUtils.isNotEmpty(resources.getId())){
                resourcesService.update(resources);
                //更新权限
                shiroService.updatePermission();
            }else{
                resourcesService.add(resources);
                //更新权限
                shiroService.updatePermission();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            resourcesService.delete(id);
            //更新权限
            shiroService.updatePermission();
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
            resourcesService.batchDel(ids);
            //更新权限
            shiroService.updatePermission();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("批量删除出错！");
            return result;
        }
    }

}
