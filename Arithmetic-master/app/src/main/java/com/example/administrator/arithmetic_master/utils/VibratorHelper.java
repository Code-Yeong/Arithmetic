package com.example.administrator.arithmetic_master.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * @author guoqingyun
 * 作用：使手机产生振动
 */
public class VibratorHelper {

    public static void vibrate(Context context, long milliseonds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseonds);
    }

    public static void vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }
}
