package com.demo.model.sys;

import java.io.Serializable;

/**
 * 资源类
 * Created by chenzhi on 2017/11/10 0010.
 */
public class Resources implements Serializable{

    private static final long serialVersionUID = -7579188495963560932L;

    private String id;
    private String resUrl;      //资源地址
    private String name;        //资源名称
    private String type;        //类型  1:：菜单  2：按钮
    private String parentId;    //资源父id
    private String sort;        //排序

    private String typeName;    //类型名称
    private String checked;     //是否选中

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
