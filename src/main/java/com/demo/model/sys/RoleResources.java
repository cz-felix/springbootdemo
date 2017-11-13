package com.demo.model.sys;

import java.io.Serializable;

/**
 * 角色资源
 * Created by chenzhi on 2017/11/10 0010.
 */
public class RoleResources implements Serializable{

    private static final long serialVersionUID = -4248800064866929509L;

    private String roleId;           //角色ID
    private String resourcesId;     //资源ID

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }
}
