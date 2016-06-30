package com.lzb.oa.entity;

import java.io.Serializable;

/**
 * Created by lvzhenbin on 2015/10/10.
 * 显示任务信息实例
 */
public class TaskInfoEntity implements Serializable{

    private String roomer_no;       //客户编号
    private String roomer_name;     //客户姓名
    private String roomer_sex;      //客户性别
    private String roomer_phone_no; //客户手机号码
    private String roomer_house_no;  //房源编号
    private String roomer_date;       //看房日期
    private String roomer_period;   //看房时间段
    private String roomer_rent;     //客户需求
    private String roomer_complete;  //交易完成表示
    private String roomer_emp_no;   //跟进员工
    private String house_city;
    private String house_address;

    public String getRoomer_no() {
        return roomer_no;
    }

    public void setRoomer_no(String roomer_no) {
        this.roomer_no = roomer_no;
    }

    public String getRoomer_name() {
        return roomer_name;
    }

    public void setRoomer_name(String roomer_name) {
        this.roomer_name = roomer_name;
    }

    public String getRoomer_sex() {
        return roomer_sex;
    }

    public void setRoomer_sex(String roomer_sex) {
        this.roomer_sex = roomer_sex;
    }

    public String getRoomer_phone_no() {
        return roomer_phone_no;
    }

    public void setRoomer_phone_no(String roomer_phone_no) {
        this.roomer_phone_no = roomer_phone_no;
    }

    public String getRoomer_house_no() {
        return roomer_house_no;
    }

    public void setRoomer_house_no(String roomer_house_no) {
        this.roomer_house_no = roomer_house_no;
    }

    public String getRoomer_date() {
        return roomer_date;
    }

    public void setRoomer_date(String roomer_date) {
        this.roomer_date = roomer_date;
    }

    public String getRoomer_period() {
        return roomer_period;
    }

    public void setRoomer_period(String roomer_period) {
        this.roomer_period = roomer_period;
    }

    public String getRoomer_rent() {
        return roomer_rent;
    }

    public void setRoomer_rent(String roomer_rent) {
        this.roomer_rent = roomer_rent;
    }

    public String getRoomer_complete() {
        return roomer_complete;
    }

    public void setRoomer_complete(String roomer_complete) {
        this.roomer_complete = roomer_complete;
    }

    public String getRoomer_emp_no() {
        return roomer_emp_no;
    }

    public void setRoomer_emp_no(String roomer_emp_no) {
        this.roomer_emp_no = roomer_emp_no;
    }

    public String getHouse_city() {
        return house_city;
    }

    public void setHouse_city(String house_city) {
        this.house_city = house_city;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public TaskInfoEntity(String roomer_no, String roomer_name, String roomer_sex, String roomer_phone_no, String roomer_house_no, String roomer_date, String roomer_period, String roomer_rent, String roomer_complete, String roomer_emp_no, String house_city, String house_address) {
        this.roomer_no = roomer_no;
        this.roomer_name = roomer_name;
        this.roomer_sex = roomer_sex;
        this.roomer_phone_no = roomer_phone_no;
        this.roomer_house_no = roomer_house_no;
        this.roomer_date = roomer_date;
        this.roomer_period = roomer_period;
        this.roomer_rent = roomer_rent;
        this.roomer_complete = roomer_complete;
        this.roomer_emp_no = roomer_emp_no;
        this.house_city = house_city;
        this.house_address = house_address;
    }

    public TaskInfoEntity() {
    }


}
