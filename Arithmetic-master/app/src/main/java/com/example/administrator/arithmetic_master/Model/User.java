package com.example.administrator.arithmetic_master.Model;

/**
 * Created by Administrator on 2016/10/13.
 */
public class User {

    public int currentExeGrade;
    public int currentExeNum;



    private String userName;
    private int userGrade;
    private String userId;
    private int userFlag;
    private String UserLoginName;
    private String sex;
    private String email;
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private static final User _singleton=new User();
    protected void User(){}

    public static User getInstance()
    {
        return _singleton;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public static User get_singleton() {
        return _singleton;
    }
}
