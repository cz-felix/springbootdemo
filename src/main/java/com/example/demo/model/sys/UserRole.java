package com.example.demo.model.sys;

import java.io.Serializable;

public class UserRole implements Serializable{

    private static final long serialVersionUID = -916411139749530670L;

    private String userid;

    private String roleid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}