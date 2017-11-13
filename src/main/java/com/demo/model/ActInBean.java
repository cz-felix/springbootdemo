package com.demo.model;

import java.util.List;

/**
 * Created by chenzhi on 2017/10/19 0019.
 */
public class ActInBean {

    private int page;
    private int limit;

    //当前用户相关的信息
    private String userId;
    private List<String> userRoles;

    //下面两个参数退回时使用
    private String currentTaskId;
    private String returnToTaskKey;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public String getReturnToTaskKey() {
        return returnToTaskKey;
    }

    public void setReturnToTaskKey(String returnToTaskKey) {
        this.returnToTaskKey = returnToTaskKey;
    }
}
