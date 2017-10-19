package com.example.demo.model.sys;

import java.io.Serializable;

public class Role implements Serializable{

    private static final long serialVersionUID = -6140090613812307452L;
    private String id;

    private String roledesc;

    private Integer selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return roleDesc
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * @param roledesc
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}