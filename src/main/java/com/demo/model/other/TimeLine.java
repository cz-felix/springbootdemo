package com.demo.model.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author chenzhi
 * @date 2018/1/25 0025
 * @description
 */
public class TimeLine implements Serializable{

    private static final long serialVersionUID = -8878417604583050127L;

    private String id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private byte[] content;

    private String contentStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentStr() {
        if(null!=content){
            contentStr = new String(content);
        }
        return contentStr;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", content=" + Arrays.toString(content) +
                ", contentStr='" + contentStr + '\'' +
                '}';
    }
}
