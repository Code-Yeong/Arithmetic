package com.example.administrator.arithmetic_master.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/22.
 */
public class DisplayMsg {
    public static void Show(Context context,String str)
    {
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
