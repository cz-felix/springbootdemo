package com.example.demo.utils.ChainOfResponsibility;

/**
 * Created by chenzhi on 2017/9/11.
 */
public class HandlerContext {
    private String operateType = "";
    private String taskId = "";

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
