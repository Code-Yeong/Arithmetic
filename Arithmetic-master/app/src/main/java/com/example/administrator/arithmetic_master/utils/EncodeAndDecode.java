package com.example.administrator.arithmetic_master.utils;

/**
 * Created by Administrator on 2016/10/23.
 */
public class EncodeAndDecode {
    public static String Encode(String origin)
    {
        String str=origin.replace('+','A');
        str=str.replace("-","B");
        str=str.replace('*','C');
        str=str.replace("/","D");
        str=str.replace(".","E");
        str=str.replace('(','F');
        str=str.replace(')','G');
        str=str.replace("|","H");
        return str;
    }

    public static String Decode(String origin)
    {
        String str=origin.replace('A','+');
        str=str.replace("B","-");
        str=str.replace('C','*');
        str=str.replace("D","/");
        str=str.replace("E",".");
        str=str.replace('F','(');
        str=str.replace('G',')');
        str=str.replace("H","|");
        return str;
    }
}
