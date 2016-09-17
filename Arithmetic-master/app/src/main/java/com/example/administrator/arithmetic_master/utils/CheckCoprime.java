package com.example.administrator.arithmetic_master.utils;

public class CheckCoprime {
    /**
     * @param m,n 两个正整数
     * @return true:m、n互质 ，false:m、n不互为质数
     * */
    public static boolean isCoprime(int m,int n){
        int a=0;
        int b=0;
        int c=0;
        if(m > n) {
            a = m;
            b = n;
        }else{
            a=n;
            b=m;
        }
        while((c = a % b) != 0) {
            a = b;
            b = c;
        }
        if(b==1){
            return true;
        }
        return false;
    }
}
