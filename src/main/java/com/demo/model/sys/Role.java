package com.demo.model.sys;

import com.alibaba.fastjson.annotation.JSONField;

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

    @JSONField(name= "LAY_CHECKED")
    private boolean layChecked;

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
        if (selected != null){
            if(1 == selected){
                this.setLayChecked(true);
            }else{
                this.setLayChecked(false);
            }
        }
        this.selected = selected;
    }

    public boolean isLayChecked() {
        return layChecked;
    }

    public void setLayChecked(boolean layChecked) {
        this.layChecked = layChecked;
    }
}
