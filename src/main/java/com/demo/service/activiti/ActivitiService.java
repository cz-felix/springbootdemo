package com.demo.service.activiti;

import com.demo.model.activiti.ActInBean;
import com.demo.utils.common.Constant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhi
 * @date 2018/1/22 0022
 * @description
 */
@Service
public class ActivitiService {

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

    /**
     *获取未部署(最后一个版本)的模型列表
     * @param actInBean
     * @return
     */
    public PageInfo<Model> getModelList(ActInBean actInBean){
        ModelQuery modelQuery = null;
        if(StringUtils.isNotEmpty(actInBean.getModelName())){
            modelQuery = repositoryService.createModelQuery().notDeployed().latestVersion().modelNameLike("%"+actInBean.getModelName()+"%").orderByLastUpdateTime().desc();
        }else{
            modelQuery = repositoryService.createModelQuery().notDeployed().latestVersion().orderByLastUpdateTime().desc();
        }
        Long resultCount = modelQuery.count();
        int page = actInBean.getPage() > 0 ? actInBean.getPage() - 1 : 0;
        int limit = actInBean.getLimit() > 0 ? actInBean.getLimit() : 10;
        int startRow = page * limit;
        List<Model> modelList = modelQuery.listPage(startRow,limit);
        PageInfo<Model> modelPageInfo = new PageInfo(modelList);
        modelPageInfo.setTotal(resultCount);
        return modelPageInfo;
    }

    /**
     * 创建模型基本信息
     * @param actInBean
     * @return
     */
    @Transactional
    public String createModelinfo(ActInBean actInBean){
        //创建模型对象
        Model model = repositoryService.newModel();
        model.setName(actInBean.getModelName());
        model.setKey(actInBean.getModelKey());
        model.setCategory("1");

        Integer version = Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count() + 1));
        model.setVersion(version);
        //设置model的metaInfo
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode metaInfo = objectMapper.createObjectNode();
        //添加model的metaInfo信息
        metaInfo.put("name", model.getName());
        metaInfo.put("revision", model.getVersion());
        metaInfo.put("description", actInBean.getModelDesc());
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
        propertiesObject.put("documentation", actInBean.getModelDesc());
        propertiesObject.put("process_id", model.getKey());
        String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        propertiesObject.put("process_author", userid);
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
     * 删除模型
     * @param modelId
     */
    @Transactional
    public void deleteModel(String modelId){
        Model model = repositoryService.getModel(modelId);
        repositoryService.deleteModel(modelId);
    }

    /**
     * 批量删除模型
     * @param modelIds
     */
    @Transactional
    public void batchDelModel(String[] modelIds){
        for (String id : modelIds) {
            repositoryService.deleteModel(id);
        }
    }

    /**
     * 部署模型
     * @param modelId
     */
    @Transactional
    public void deployModel(String modelId){
        Model model = repositoryService.getModel(modelId);
        String processName = model.getName();
        if (!StringUtils.endsWith(processName, Constant.ACTIVITI_POSTFIX)) {
            processName += Constant.ACTIVITI_POSTFIX;
        }
        //获取模型对应的BpmnModel
        byte[] modelEditorSourceData = repositoryService.getModelEditorSource(model.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode modelEditorSourceNode = null;
        try {
            modelEditorSourceNode = objectMapper.readTree(modelEditorSourceData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("部署模型失败");
        }
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelEditorSourceNode);
        //部署
        Deployment deployment = repositoryService.createDeployment().category(model.getCategory()).name(model.getId()).addBpmnModel(processName, bpmnModel).deploy();
        //模型设置部署ID
        model.setDeploymentId(deployment.getId());
        //保存模型
        repositoryService.saveModel(model);
        //设置流程分类
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        if (processDefinition != null) {
            repositoryService.setProcessDefinitionCategory(processDefinition.getId(), model.getCategory());
        }
    }

    /**
     *获取已部署的流程定义列表
     * @param actInBean
     * @return
     */
    public PageInfo<Map<String,Object>> getProcessDefList(ActInBean actInBean){
        ProcessDefinitionQuery processDefinitionQuery = null;
        if(StringUtils.isNotEmpty(actInBean.getModelName())){
            processDefinitionQuery = repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%"+actInBean.getModelName()+"%").orderByProcessDefinitionKey().desc();
        }else{
            processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().desc();
        }
        Long resultCount = processDefinitionQuery.count();
        int page = actInBean.getPage() > 0 ? actInBean.getPage() - 1 : 0;
        int limit = actInBean.getLimit() > 0 ? actInBean.getLimit() : 10;
        int startRow = page * limit;
        List<ProcessDefinition> processDefList = processDefinitionQuery.listPage(startRow,limit);
        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        for (ProcessDefinition processDefinition : processDefList) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",processDefinition.getId());
            map.put("name",processDefinition.getName());
            map.put("key",processDefinition.getKey());
            map.put("diagramResourceName",processDefinition.getDiagramResourceName());
            map.put("version",processDefinition.getVersion());
            mapList.add(map);
        }
        PageInfo<Map<String,Object>> processDefPageInfo = new PageInfo(mapList);
        processDefPageInfo.setTotal(resultCount);
        return processDefPageInfo;
    }

    /**
     * 获取部署后的流程图
     * @param processDefId
     * @return
     */
    public byte[] processDefDiagram(String processDefId)throws IOException{
        InputStream resourceAsStream = repositoryService.getProcessDiagram(processDefId);
        byte[] bytes = this.input2byte(resourceAsStream);
        return bytes;
    }

    /**
     * inputStream转Byte[]
     * @param inStream
     * @return
     * @throws IOException
     */
    public byte[] input2byte(InputStream inStream) throws IOException {
        byte[] byt = new byte[inStream.available()];
        inStream.read(byt);
         return byt;
     }
}
