package com.lzb.oa.entity;

/**
 * Created by Administrator on 2016/5/24.
 */
public class NoticeEntity {

    private String theme;
    private String content;
    private String time;
    private String empNo;

    public NoticeEntity(String theme, String content, String time, String empNo) {
        this.theme = theme;
        this.content = content;
        this.time = time;
        this.empNo = empNo;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
}
