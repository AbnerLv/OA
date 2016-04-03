package com.lzb.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvzhenbin on 2016/4/3.
 */
public class EmpInfo implements Serializable{

    private String emp_nickname;   //昵称
    private String emp_name;       //员工姓名
    private String emp_sex;        //员工性别
    private String emp_age;        //员工年龄
    private String emp_phone_no;   //员工手机号
    private String emp_email;      //员工电子邮箱
    private String emp_no;         //员工编号
    private String emp_department; //员工所属部门
    private String emp_position;   //员工的职位
    private Date emp_entry_date;   //员工入职日期
    private Date emp_birthday;     //员工生日
    private String emp_nation;     //员工民簇
    private String emp_identify;   //员工的身份证号
    private String emp_city;       //员工所在城市
    private String emp_address;    //员工家庭地址
    private String emp_password;   //登录密码

    public String getEmp_nickname() {
        return emp_nickname;
    }
    public void setEmp_nickname(String emp_nickname) {
        this.emp_nickname = emp_nickname;
    }
    public String getEmp_name() {
        return emp_name;
    }
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    public String getEmp_sex() {
        return emp_sex;
    }
    public void setEmp_sex(String emp_sex) {
        this.emp_sex = emp_sex;
    }
    public String getEmp_age() {
        return emp_age;
    }
    public void setEmp_age(String emp_age) {
        this.emp_age = emp_age;
    }
    public String getEmp_phone_no() {
        return emp_phone_no;
    }
    public void setEmp_phone_no(String emp_phone_no) {
        this.emp_phone_no = emp_phone_no;
    }
    public String getEmp_email() {
        return emp_email;
    }
    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }
    public String getEmp_no() {
        return emp_no;
    }
    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }
    public String getEmp_department() {
        return emp_department;
    }
    public void setEmp_department(String emp_department) {
        this.emp_department = emp_department;
    }
    public String getEmp_position() {
        return emp_position;
    }
    public void setEmp_position(String emp_position) {
        this.emp_position = emp_position;
    }
    public Date getEmp_entry_date() {
        return emp_entry_date;
    }
    public void setEmp_entry_date(Date emp_entry_date) {
        this.emp_entry_date = emp_entry_date;
    }
    public Date getEmp_birthday() {
        return emp_birthday;
    }
    public void setEmp_birthday(Date emp_birthday) {
        this.emp_birthday = emp_birthday;
    }
    public String getEmp_nation() {
        return emp_nation;
    }
    public void setEmp_nation(String emp_nation) {
        this.emp_nation = emp_nation;
    }
    public String getEmp_identify() {
        return emp_identify;
    }
    public void setEmp_identify(String emp_identify) {
        this.emp_identify = emp_identify;
    }
    public String getEmp_city() {
        return emp_city;
    }
    public void setEmp_city(String emp_city) {
        this.emp_city = emp_city;
    }
    public String getEmp_address() {
        return emp_address;
    }
    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }
    public String getEmp_password() {
        return emp_password;
    }
    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }
    public EmpInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    public EmpInfo( String emp_nickname, String emp_name,
                    String emp_sex, String emp_age, String emp_phone_no,
                    String emp_email, String emp_no, String emp_department,
                    String emp_position, Date emp_entry_date, Date emp_birthday,
                    String emp_nation, String emp_identify, String emp_city,
                    String emp_address, String emp_password) {
        super();

        this.emp_nickname = emp_nickname;
        this.emp_name = emp_name;
        this.emp_sex = emp_sex;
        this.emp_age = emp_age;
        this.emp_phone_no = emp_phone_no;
        this.emp_email = emp_email;
        this.emp_no = emp_no;
        this.emp_department = emp_department;
        this.emp_position = emp_position;
        this.emp_entry_date = emp_entry_date;
        this.emp_birthday = emp_birthday;
        this.emp_nation = emp_nation;
        this.emp_identify = emp_identify;
        this.emp_city = emp_city;
        this.emp_address = emp_address;
        this.emp_password = emp_password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpInfo empInfo = (EmpInfo) o;

        if (emp_nickname != null ? !emp_nickname.equals(empInfo.emp_nickname) : empInfo.emp_nickname != null)
            return false;
        if (emp_name != null ? !emp_name.equals(empInfo.emp_name) : empInfo.emp_name != null)
            return false;
        if (emp_sex != null ? !emp_sex.equals(empInfo.emp_sex) : empInfo.emp_sex != null)
            return false;
        if (emp_age != null ? !emp_age.equals(empInfo.emp_age) : empInfo.emp_age != null)
            return false;
        if (emp_phone_no != null ? !emp_phone_no.equals(empInfo.emp_phone_no) : empInfo.emp_phone_no != null)
            return false;
        if (emp_email != null ? !emp_email.equals(empInfo.emp_email) : empInfo.emp_email != null)
            return false;
        if (emp_no != null ? !emp_no.equals(empInfo.emp_no) : empInfo.emp_no != null) return false;
        if (emp_department != null ? !emp_department.equals(empInfo.emp_department) : empInfo.emp_department != null)
            return false;
        if (emp_position != null ? !emp_position.equals(empInfo.emp_position) : empInfo.emp_position != null)
            return false;
        if (emp_entry_date != null ? !emp_entry_date.equals(empInfo.emp_entry_date) : empInfo.emp_entry_date != null)
            return false;
        if (emp_birthday != null ? !emp_birthday.equals(empInfo.emp_birthday) : empInfo.emp_birthday != null)
            return false;
        if (emp_nation != null ? !emp_nation.equals(empInfo.emp_nation) : empInfo.emp_nation != null)
            return false;
        if (emp_identify != null ? !emp_identify.equals(empInfo.emp_identify) : empInfo.emp_identify != null)
            return false;
        if (emp_city != null ? !emp_city.equals(empInfo.emp_city) : empInfo.emp_city != null)
            return false;
        if (emp_address != null ? !emp_address.equals(empInfo.emp_address) : empInfo.emp_address != null)
            return false;
        return !(emp_password != null ? !emp_password.equals(empInfo.emp_password) : empInfo.emp_password != null);

    }

    @Override
    public int hashCode() {
        int result = emp_nickname != null ? emp_nickname.hashCode() : 0;
        result = 31 * result + (emp_name != null ? emp_name.hashCode() : 0);
        result = 31 * result + (emp_sex != null ? emp_sex.hashCode() : 0);
        result = 31 * result + (emp_age != null ? emp_age.hashCode() : 0);
        result = 31 * result + (emp_phone_no != null ? emp_phone_no.hashCode() : 0);
        result = 31 * result + (emp_email != null ? emp_email.hashCode() : 0);
        result = 31 * result + (emp_no != null ? emp_no.hashCode() : 0);
        result = 31 * result + (emp_department != null ? emp_department.hashCode() : 0);
        result = 31 * result + (emp_position != null ? emp_position.hashCode() : 0);
        result = 31 * result + (emp_entry_date != null ? emp_entry_date.hashCode() : 0);
        result = 31 * result + (emp_birthday != null ? emp_birthday.hashCode() : 0);
        result = 31 * result + (emp_nation != null ? emp_nation.hashCode() : 0);
        result = 31 * result + (emp_identify != null ? emp_identify.hashCode() : 0);
        result = 31 * result + (emp_city != null ? emp_city.hashCode() : 0);
        result = 31 * result + (emp_address != null ? emp_address.hashCode() : 0);
        result = 31 * result + (emp_password != null ? emp_password.hashCode() : 0);
        return result;
    }
}
