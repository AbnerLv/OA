package com.lzb.oa.entity;

import java.io.Serializable;

/**
 * Created by lvzhenbin on 2015/10/10.
 * 显示任务信息实例
 */
public class TaskInfo implements Serializable{

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

    public TaskInfo(String roomer_no, String roomer_name, String roomer_sex, String roomer_phone_no, String roomer_house_no, String roomer_date, String roomer_period, String roomer_rent, String roomer_complete, String roomer_emp_no, String house_city, String house_address) {
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

    public TaskInfo() {
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "roomer_no='" + roomer_no + '\'' +
                ", roomer_name='" + roomer_name + '\'' +
                ", roomer_sex='" + roomer_sex + '\'' +
                ", roomer_phone_no='" + roomer_phone_no + '\'' +
                ", roomer_house_no='" + roomer_house_no + '\'' +
                ", roomer_date='" + roomer_date + '\'' +
                ", roomer_period='" + roomer_period + '\'' +
                ", roomer_rent='" + roomer_rent + '\'' +
                ", roomer_complete='" + roomer_complete + '\'' +
                ", roomer_emp_no='" + roomer_emp_no + '\'' +
                ", house_city='" + house_city + '\'' +
                ", house_address='" + house_address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfo taskInfo = (TaskInfo) o;

        if (roomer_no != null ? !roomer_no.equals(taskInfo.roomer_no) : taskInfo.roomer_no != null)
            return false;
        if (roomer_name != null ? !roomer_name.equals(taskInfo.roomer_name) : taskInfo.roomer_name != null)
            return false;
        if (roomer_sex != null ? !roomer_sex.equals(taskInfo.roomer_sex) : taskInfo.roomer_sex != null)
            return false;
        if (roomer_phone_no != null ? !roomer_phone_no.equals(taskInfo.roomer_phone_no) : taskInfo.roomer_phone_no != null)
            return false;
        if (roomer_house_no != null ? !roomer_house_no.equals(taskInfo.roomer_house_no) : taskInfo.roomer_house_no != null)
            return false;
        if (roomer_date != null ? !roomer_date.equals(taskInfo.roomer_date) : taskInfo.roomer_date != null)
            return false;
        if (roomer_period != null ? !roomer_period.equals(taskInfo.roomer_period) : taskInfo.roomer_period != null)
            return false;
        if (roomer_rent != null ? !roomer_rent.equals(taskInfo.roomer_rent) : taskInfo.roomer_rent != null)
            return false;
        if (roomer_complete != null ? !roomer_complete.equals(taskInfo.roomer_complete) : taskInfo.roomer_complete != null)
            return false;
        if (roomer_emp_no != null ? !roomer_emp_no.equals(taskInfo.roomer_emp_no) : taskInfo.roomer_emp_no != null)
            return false;
        if (house_city != null ? !house_city.equals(taskInfo.house_city) : taskInfo.house_city != null)
            return false;
        return !(house_address != null ? !house_address.equals(taskInfo.house_address) : taskInfo.house_address != null);

    }

    @Override
    public int hashCode() {
        int result = roomer_no != null ? roomer_no.hashCode() : 0;
        result = 31 * result + (roomer_name != null ? roomer_name.hashCode() : 0);
        result = 31 * result + (roomer_sex != null ? roomer_sex.hashCode() : 0);
        result = 31 * result + (roomer_phone_no != null ? roomer_phone_no.hashCode() : 0);
        result = 31 * result + (roomer_house_no != null ? roomer_house_no.hashCode() : 0);
        result = 31 * result + (roomer_date != null ? roomer_date.hashCode() : 0);
        result = 31 * result + (roomer_period != null ? roomer_period.hashCode() : 0);
        result = 31 * result + (roomer_rent != null ? roomer_rent.hashCode() : 0);
        result = 31 * result + (roomer_complete != null ? roomer_complete.hashCode() : 0);
        result = 31 * result + (roomer_emp_no != null ? roomer_emp_no.hashCode() : 0);
        result = 31 * result + (house_city != null ? house_city.hashCode() : 0);
        result = 31 * result + (house_address != null ? house_address.hashCode() : 0);
        return result;
    }
}
