package com.tidy.tidyegg.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by qiang on 2016/10/8.
 * SharedPreferences工具类
 */
public class SpUtils {
    /**
     * @param mContext 上下文，来区别哪一个activity调用的
     * @param whichSp 使用的SharedPreferences的名字
     * @param field SharedPreferences的哪一个字段
     */

    /**
     * 取出whichSp中field字段对应的string类型的值
     */
    public static String getSharePreStr(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        String s = sp.getString(field, "");// 如果该字段没对应值,则取出空字符串
        return s;
    }

    /**
     * 取出whichSp中field字段对应的int类型的值
     */
    public static int getSharePreInt(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        int i = sp.getInt(field, 0);// 如果该字段没对应值,则取出0
        return i;
    }

    /**
     * 取出whichSp中field字段对应的boolean类型的值
     */
    public static boolean getSharePreBoolean(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        boolean b = sp.getBoolean(field, false);// 如果该字段没对应值,则默认为false
        return b;
    }

    /**
     * 取出whichSp中field字段对应的long类型的值
     */
    public static long getSharePreLong(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        long l = sp.getLong(field, 0);// 如果该字段没对应值,则取出0
        return l;
    }

    /**
     * 保存string类型的value到whichSp中的field字段
     */
    public static void putSharePre(Context mContext, String whichSp, String field, String value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putString(field, value).commit();
    }

    /**
     * 保存int类型的value到whichSp中的field字段
     */
    public static void putSharePre(Context mContext, String whichSp, String field, int value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putInt(field, value).commit();
    }

    /**
     * 保存boolean类型的value到whichSp中的field字段
     */
    public static void putSharePre(Context mContext, String whichSp, String field, boolean value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putBoolean(field, value).commit();
    }

    /**
     * 保存long类型的value到whichSp中的field字段
     */
    public static void putSharePre(Context mContext, String whichSp, String field, long value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putLong(field, value).commit();
    }

    /**
     * 清除SharedPreferences数据
     */
    public static void clear(Context mContext, String whichSp) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().clear().commit();
    }

    /**
     * 获取城市名
     */
    public static String getCityName(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        String cityName = sp.getString(field, "上海市");// 如果该字段没对应值,则cityName默认为上海市
        return cityName;
    }


    /**
     * 将对象保存在SharedPreferences中
     *
     * @param context 上下文
     * @param object  对象
     */
    public static void putObject(Context context, String spName, String key, Object object) {
        String value = code(object);
        context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    /**
     * 将对象从SharedPreferences中取出
     *
     * @param context 上下文
     * @return
     */
    public static Object getObject(Context context, String spName, String key) {
        String code = context.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(key, null);
        return decode(code);
    }

    /**
     * 编码
     *
     * @param object 对象
     * @return
     */
    public static String code(Object object) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        String code = null;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(object);
            code = new String(Base64.encodeBase64(stream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 解码
     *
     * @param code
     * @return
     */
    public static Object decode(String code) {
        if (code == null)
            return null;
        Object object = null;
        byte[] base64 = Base64.decodeBase64(code.getBytes());
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            ObjectInputStream bis = new ObjectInputStream(bais);
            object = bis.readObject();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

}
