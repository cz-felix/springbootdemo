package com.demo.controller.activiti;

import com.demo.model.activiti.ActInBean;
import com.demo.model.sys.User;
import com.demo.service.activiti.ActivitiService;
import com.demo.service.activiti.ActivitiUtil;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模型管理
 * @author chenzhi
 * @date 2018/1/22 0022
 * @description
 */
@RestController
@RequestMapping("/activiti/model")
public class ModelController {

    @Autowired
    private ActivitiService activitiService;

    @RequestMapping("/toModelList")
    public ModelAndView toModelList() {
        ModelAndView modelAndView = new ModelAndView("activiti/modelList");
        return modelAndView;
    }

    @RequestMapping("/getModelList")
    public PageEntity getModelList(ActInBean actInBean){
        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = activitiService.getModelList(actInBean);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取模型列表出错！");
        }
        return pageEntity;
    }

    /**
     * 跳转至模型基本信息
     * @return
     */
    @RequestMapping("/toModelInfo")
    public ModelAndView toModelInfo(String userId){
        ModelAndView model = new ModelAndView("activiti/modelInfo");
        return  model;
    }

    /**
     * 保存模型基本信息
     * @param actInBean
     * @return
     */
    @RequestMapping("/saveModelInfo")
    public Result saveModelInfo(ActInBean actInBean){
        Result result = new Result();
        try {
            String modelId = activitiService.createModelinfo(actInBean);
            result.setData(modelId);
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("保存模型基本信息出错");
        }
        return result;
    }

    /**
     * 部署流程
     * @param modelId
     * @return
     */
    @RequestMapping("/deployModel")
    public Result deployModel(String modelId){
        Result result = new Result();
        try {
            activitiService.deployModel(modelId);
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("部署流程出错");
        }
        return  result;
    }

    @RequestMapping(value = "/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            activitiService.deleteModel(id);
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
            activitiService.batchDelModel(ids);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("批量删除出错！");
            return result;
        }
    }


}
