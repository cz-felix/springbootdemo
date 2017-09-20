package com.example.demo.controller;/**
 * Created by Administrator on 2017/8/2.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

/**
 * @author donghuan
 * @create 2017-08-02 10:45
 **/
@RestController
public class ActDesingnerController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ActDesingnerController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(
            value = {"/model/{modelId}/json"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public ObjectNode getEditorJson(@PathVariable String modelId) {

        ObjectNode modelNode = null;
        Model model = this.repositoryService.getModel(modelId);
        if(model != null) {
            try {
                if(StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode)this.objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = this.objectMapper.createObjectNode();
                    modelNode.put("name", model.getName());
                }

                modelNode.put("modelId", model.getId());
                ObjectNode editorJsonNode = (ObjectNode)this.objectMapper.readTree(new String(this.repositoryService.getModelEditorSource(model.getId()), "utf-8"));
              //  modelNode.put("model", editorJsonNode);
                modelNode.putPOJO("model", editorJsonNode);
            } catch (Exception var5) {
                LOGGER.error("Error creating model JSON", var5);
                throw new ActivitiException("Error creating model JSON", var5);
            }
        }

        return modelNode;
    }

    @RequestMapping(
            value = {"/editor/stencilset"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");

        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception var3) {
            throw new ActivitiException("Error while loading stencil set", var3);
        }
    }
}
