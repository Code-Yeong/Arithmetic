package com.example.administrator.arithmetic_master.utils;

/**
 * @author guoqingyun
 */
public class CheckPhone {

    /**
     * @param phone 手机号
     * @return true:手机号合法；false:手机号不合法
     * */
    public static boolean check(String phone){
        String reg="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if(phone.matches(reg)){
            return true;
        }else{
            return false;
        }
    }
}
