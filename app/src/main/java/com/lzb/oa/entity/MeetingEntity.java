package com.lzb.oa.entity;

import java.io.Serializable;

/**
 * Created by lzb on 2016/5/23.
 */
public class MeetingEntity implements Serializable {

    private String theme;
    private String time;
    private String endTime;
    private String content;
    private String address;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
