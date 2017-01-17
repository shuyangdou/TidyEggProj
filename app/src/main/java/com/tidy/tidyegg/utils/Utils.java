package com.tidy.tidyegg.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

/**
 * Created by qiang on 2016/10/8.
 * 工具类
 */
public class Utils {

    /**
     * 判断网络连接状态
     *
     * @param mContext 上下文
     * @return
     */
    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return ni.isAvailable();
        }
        return false;
    }

    /**
     * 控制组件的显示
     */
    public static void show(View... view) {
        for (View v : view) {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 控制组件的隐藏
     */
    public static void gone(View... view) {
        for (View v : view) {
            v.setVisibility(View.GONE);
        }
    }

    /**
     * 控制组件的隐藏
     */
    public static void inVisible(View... view) {
        for (View v : view) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 根据未来的某个时间的毫秒数实现倒计时
     *
     * @param time 毫秒数
     * @return
     */
    public static String countdown(long time) {
        String result = "0天00时00分00秒";
        long current = System.currentTimeMillis();
        long l = (time * 1000) - current;
        if (l > 0) {
            return Utils.format(l);
        }
        return result;
    }

    /**
     * 将毫秒数转换为 **天**时**分**秒 后的格式
     *
     * @param ms 要转换的毫秒数
     * @return
     */
    public static String format(long ms) {
        String daysString;
        String hoursString;
        String minutesString;
        String secondsString;

        long days = ms / (1000 * 60 * 60 * 24);
        if (days > 0 && days < 10)
            daysString = "0" + days;
        else if (days > 10)
            daysString = days + "";
        else
            daysString = "00";

        long hours = (ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (hours > 0 && hours < 10)
            hoursString = "0" + hours;
        else if (hours > 10)
            hoursString = hours + "";
        else
            hoursString = "00";

        long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
        if (minutes > 0 && minutes < 10)
            minutesString = "0" + minutes;
        else if (minutes > 10)
            minutesString = minutes + "";
        else
            minutesString = "00";

        long seconds = (ms % (1000 * 60)) / 1000;
        if (seconds > 0 && seconds < 10)
            secondsString = "0" + seconds;
        else if (seconds > 10)
            secondsString = seconds + "";
        else
            secondsString = "00";

        return daysString + "天" + hoursString + "时" + minutesString + "分"
                + secondsString + "秒";
    }

    /**
     * 自定义setCompoundDrawables替换TextView上方图片
     *
     * @param textView RadioButton
     * @param top      Drawable资源
     */
    public static void setCompoundDrawablesTop(TextView textView, Drawable top) {
        if (top != null) {
            top.setBounds(0, 0, top.getIntrinsicWidth(), top.getIntrinsicHeight());
        }
        textView.setCompoundDrawables(null, top, null, null);// 位置是相对应文字来决定的
    }
}
