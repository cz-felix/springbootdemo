package com.demo.model.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * Created by chenzhi on 2017/11/10 0010.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 7198260479883785891L;

    private String id;
    private String username;         //用户名
    private String nickname;         //昵称
    private String pwd;              //密码
    private Date lastLoginTime;     //最后登录时间
    private String status;          //状态   1:有效，0:禁止登录
    private String createNameId;    //创建人id
    private Date createTime;        //创建时间
    private String lastUpdateNameId;//最后修改人Id
    private Date lastUpdateTime;//最后修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateNameId() {
        return createNameId;
    }

    public void setCreateNameId(String createNameId) {
        this.createNameId = createNameId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateNameId() {
        return lastUpdateNameId;
    }

    public void setLastUpdateNameId(String lastUpdateNameId) {
        this.lastUpdateNameId = lastUpdateNameId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
