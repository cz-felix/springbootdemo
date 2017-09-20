package com.example.demo.utils.ChainOfResponsibility;

/**
 * Created by chenzhi on 2017/8/17.
 */
public class AuditBean {

    private String taskId;
    private String hisTaskId;
    private String returnToTaskKey;
    private String approvalAction;//审批动作
    private String auditPeople; //审批人
    private String auditOpinion; //审批说明

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHisTaskId() {
        return hisTaskId;
    }

    public void setHisTaskId(String hisTaskId) {
        this.hisTaskId = hisTaskId;
    }

    public String getReturnToTaskKey() {
        return returnToTaskKey;
    }

    public void setReturnToTaskKey(String returnToTaskKey) {
        this.returnToTaskKey = returnToTaskKey;
    }

    public String getApprovalAction() {
        return approvalAction;
    }

    public void setApprovalAction(String approvalAction) {
        this.approvalAction = approvalAction;
    }

    public String getAuditPeople() {
        return auditPeople;
    }

    public void setAuditPeople(String auditPeople) {
        this.auditPeople = auditPeople;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}
