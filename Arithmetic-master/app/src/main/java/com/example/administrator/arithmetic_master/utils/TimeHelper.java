package com.example.administrator.arithmetic_master.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guoqingyun
 */
public class TimeHelper {
    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式为 yyyy-MM-dd hh:mm:ss
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date().getTime());
    }

    /**
     * 获取当前日期
     *
     * @return 返回当前时间，格式为 yyyy-MM-dd
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date().getTime());
    }

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式为 yyyy年MM月dd日
     */
    public static String getFormatNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        return "上一次更新:" + sdf.format(date);
    }
}
