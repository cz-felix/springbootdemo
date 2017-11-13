package com.demo.service;

import com.demo.model.ActInBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.*;
import org.activiti.engine.repository.Model;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by chenzhi on 2017/9/8.
 */
@Service
public class ActivitiUtil {

    private static Logger logger = LoggerFactory.getLogger(ActivitiUtil.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;
    @Autowired
    private ManagementService managementService;

    public void contextLoads() {
        List<Model> modelList = repositoryService.createModelQuery().modelKey("processTest").list();
        System.out.println(modelList.size());
    }

    /**
     * 模型创建
     */
    public String model_create(){
        //创建模型对象
        Model model = repositoryService.newModel();
        model.setName("流程模型");
        model.setKey("processTest");
        model.setCategory("1");

        Integer version = Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count() + 1));
        model.setVersion(version);
        //设置model的metaInfo
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode metaInfo = objectMapper.createObjectNode();
        //添加model的metaInfo信息
        metaInfo.put("name", model.getName());
        metaInfo.put("revision", model.getVersion());
        metaInfo.put("description", "模型测试");
        model.setMetaInfo(metaInfo.toString());
        //保存模型
        repositoryService.saveModel(model);
        //设置模型source信息
        ObjectNode editorNode = objectMapper.createObjectNode();
        // model网页编辑时需要的资源信息。
        editorNode.put("resourceId", model.getId());
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.set("stencilset", stencilSetNode);

        //设置模型下面的属性信息
        ObjectNode propertiesObject = objectMapper.createObjectNode();
        propertiesObject.put("name", model.getName());
        propertiesObject.put("documentation", "模型测试");
        propertiesObject.put("process_id", model.getKey());

        propertiesObject.put("process_author", "chenzhi");
        propertiesObject.put("process_executable", "Yes");
        propertiesObject.put("process_version", model.getVersion() + "");
        propertiesObject.put("process_namespace", "");
        propertiesObject.put("executionlisteners", "");
        propertiesObject.put("eventlisteners", "");
        editorNode.set("properties", propertiesObject);
        //保存模型资源
        try {
            byte[] editorNodeBytes = editorNode.toString().getBytes("utf-8");
            repositoryService.addModelEditorSource(model.getId(), editorNodeBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return model.getId();
    }


    /**
     * 代办理任务列表
     * @param actInBean
     * @return
     */
    public PageInfo<Task> task_todoList(ActInBean actInBean){
        TaskQuery taskQuery = taskService.createTaskQuery();

        List<String> userRoleList = actInBean.getUserRoles();
        if(userRoleList == null || userRoleList.size() == 0){
            /*throw new RuntimeException("");*/
        }else{
            taskQuery.taskCandidateGroupIn(userRoleList);
        }

        String userName = actInBean.getUserId();
        if(StringUtils.isNotEmpty(userName)){
            taskQuery.taskAssignee(userName);
        }
        if(actInBean.getPage() > 0 && actInBean.getLimit()> 0){
            PageHelper.startPage(actInBean.getPage(), actInBean.getLimit());
        }
        List<Task> taskList = taskQuery.list();
        PageInfo<Task> pageInfo = new PageInfo(taskList);
        return pageInfo;
    }
}
