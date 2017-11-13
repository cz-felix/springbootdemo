package com.demo.model.sys;

import java.io.Serializable;

/**
 * 角色类
 * Created by chenzhi on 2017/11/10 0010.
 */
public class Role implements Serializable{

    private static final long serialVersionUID = -2463968117638296423L;

    private String id;
    private String name;        //角色名称
    private String roleDesc;    //角色描述
    private Integer selected;   //选中

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
