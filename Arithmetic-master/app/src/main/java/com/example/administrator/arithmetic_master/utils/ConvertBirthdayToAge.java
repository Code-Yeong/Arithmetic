package com.example.administrator.arithmetic_master.utils;

import java.util.Calendar;

/**
 * @author guoqingyun
 * 作用:将生日转换为年龄
 */
public class ConvertBirthdayToAge {

    /**
     * @param birthday 生日，格式为：yyyy-MM-dd hh:mm:ss
     * @return age 年龄
     * */
    public static String convert(String birthday){
        int age;
        int stu_year=Integer.parseInt((birthday).trim().substring(0,4));
        int stu_month=Integer.parseInt(birthday.trim().substring(5, 7));
        Calendar c=Calendar.getInstance();
        int year_now=c.get(Calendar.YEAR);
        int month_now=c.get(Calendar.MONTH)+1;
        if(stu_month>month_now){
            age=year_now-stu_year-1;
        }else{
            age=year_now-stu_year;
        }
        return String.valueOf(age);
    }
}
