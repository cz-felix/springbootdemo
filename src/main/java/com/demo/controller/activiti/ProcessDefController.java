package com.demo.controller.activiti;

import com.demo.model.activiti.ActInBean;
import com.demo.service.activiti.ActivitiService;
import com.demo.utils.common.PageEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程定义
 * @author chenzhi
 * @date 2018/1/24 0024
 * @description
 */
@RestController
@RequestMapping("/activiti/processDef")
public class ProcessDefController {

    @Autowired
    private ActivitiService activitiService;

    @RequestMapping("/toProcessDefList")
    public ModelAndView toPrrocessDefList() {
        ModelAndView modelAndView = new ModelAndView("activiti/processDefList");
        return modelAndView;
    }

    @RequestMapping("/getProcessDefList")
    public PageEntity getProcessDefList(ActInBean actInBean){
        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = activitiService.getProcessDefList(actInBean);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取流程定义列表出错！");
        }
        return pageEntity;
    }

    /**
     * 获取流程定义的流程图
     * @param processDefId
     * @return
     */
    @RequestMapping("/getProcessDefDiagramJson")
    public Map<String,Object> getProcessDefDiagramJson(String processDefId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("title","流程图");
        resultMap.put("id",processDefId);
        resultMap.put("start",0);
        List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("alt","流程图");
        dataMap.put("pid",processDefId);
        dataMap.put("src","/activiti/processDef/getProcessDefDiagramInputStream?processDefId="+processDefId);
        dataList.add(dataMap);
        resultMap.put("data",dataList);
        return resultMap;
    }

    /**
     * 获取流程图片流
     * @param processDefId
     * @return
     */
    @RequestMapping("/getProcessDefDiagramInputStream")
    public void getProcessDefDiagramInputStream(String processDefId,HttpServletResponse response)throws IOException {
        byte[] bytes = activitiService.processDefDiagram(processDefId);
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(bytes);
        stream.flush();
        stream.close();

    }
}
