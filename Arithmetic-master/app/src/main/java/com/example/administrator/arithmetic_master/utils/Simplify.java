package com.example.administrator.arithmetic_master.utils;

import java.util.ArrayList;
import java.util.List;

public class Simplify {
    /**
     * @param cop 分数的分子
     * @param deno 分数的分母
     * @return int 返回分子、分母的最大公约数
     * */
    public static int maxMultiple(int cop,int deno){
        List nums=new ArrayList();
        nums.clear();
        int max=1;
        int min = 0;
        if(cop > deno){
            min = deno;
        }else{
            min = cop;
        }
        for(int i = min; i >= 1; i--){
            if(cop % i == 0 && deno % i == 0){
                return i;
            }
        }
        return max;
    }

    /**
     * @param exp 单个分数表达式
     * @returned String 返回约分后的分数表达式
     * */
    public static String afterSimplify(String exp){
        String[] num=exp.split("/");
        int cop=Integer.parseInt(num[0]);//分子
        int deno=Integer.parseInt(num[1]);//分母
        if(cop==0){//分子为0
            return "0";
        }
        int max=0;
        while(max!=1){
            max=maxMultiple(cop, deno);//返回cop、deno的最大公约数
            cop/=max;
            deno/=max;
        }
        if(deno==1){
            return cop+"";//分数值为1
        }else{
            return cop+"/"+deno;//化简后的分数
        }
    }
}
