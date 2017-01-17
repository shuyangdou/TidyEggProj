package com.tidy.tidyegg.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by qiang on 2016/10/8.
 * Toast工具类
 */
public class ToastUtils {
    private static Toast toast;

    public static void ToastMessage(Context context, String msg) {
        if (context != null && msg != null) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    public static void ToastMessage(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context context, String msg, int time) {
        Toast.makeText(context, msg, time).show();
    }
}
