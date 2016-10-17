package com.example.administrator.arithmetic_master.Model;

/**
 * Created by Administrator on 2016/10/13.
 */
public class User {

    public int currentExeGrade;
    public int currentExeNum;



    private String userName;
    private int userGrade;



    private static final User _singleton=new User();
    protected void User(){}

    public static User getInstance()
    {
        return _singleton;
    }
}
