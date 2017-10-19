package com.example.demo.model.sys;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class RoleResources implements Serializable{

    private static final long serialVersionUID = -8559867942708057891L;

    private String roleid;

    private String resourcesid;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getResourcesid() {
        return resourcesid;
    }

    public void setResourcesid(String resourcesid) {
        this.resourcesid = resourcesid;
    }
}