package com.demo.utils.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenzhi on 2017/11/15 0015.
 */
public class PageEntity{

    public static Integer CODE_SUCCESS = Integer.valueOf(0);
    public static Integer CODE_ERROR = Integer.valueOf(1);

    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public PageEntity(){
        this.code = PageEntity.CODE_SUCCESS;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
