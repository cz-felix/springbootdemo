package com.demo.model.sys;

import java.io.Serializable;

/**
 * 用户角色关系
 * Created by chenzhi on 2017/11/11 0011.
 */
public class UserRole implements Serializable{

    private static final long serialVersionUID = -3202798869963384075L;

    private String userId;      //用户ID
    private String roleId;      //角色ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
