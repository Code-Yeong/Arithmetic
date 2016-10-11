package com.example.administrator.arithmetic_master.utils;

/**
 * @author guoqingyun
 */
public class CheckEmail {

    /**
     * @param email 邮箱地址
     * @return true:邮箱地址不合法；false:邮箱地址合法
     */
    public static boolean check(String email) {
        String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (email.matches(reg)) {
            return true;
        } else {
            return false;
        }
    }
}
