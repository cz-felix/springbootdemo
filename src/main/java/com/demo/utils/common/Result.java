package com.demo.utils.common;

/**
 * Created by chenzhi on 2017/10/12 0012.
 */
public class Result {

    public static Integer RECODE_SUCCESS = Integer.valueOf(1);
    public static Integer RECODE_ERROR = Integer.valueOf(0);
    private String errMsg;
    private Integer retCode;
    private Object data;

    public Result() {
        this.retCode = Result.RECODE_SUCCESS;
    }

    public Result(Integer retCode,String errMsg,Object data){
        this.retCode = retCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getRetCode() {
        return this.retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return " {\"errMsg\":" + this.errMsg + ", \"retCode\":" + this.retCode + ", \"data\":" + this.data + "}";
    }
}
