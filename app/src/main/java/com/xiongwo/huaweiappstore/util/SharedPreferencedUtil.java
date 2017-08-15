package com.xiongwo.huaweiappstore.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 熊，我 on 2017/8/5.
 */

public class SharedPreferencedUtil {

    private static SharedPreferences sSharedPreferences;

    private static void getSharedPreferences(Context context) {
        sSharedPreferences = context.getSharedPreferences(ConstantUtil.SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 布尔值
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        if (sSharedPreferences == null) {
            getSharedPreferences(context);
        }
        sSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sSharedPreferences == null) {
            getSharedPreferences(context);
        }
        return sSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 字符串
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        if (sSharedPreferences == null) {
            getSharedPreferences(context);
        }
        sSharedPreferences.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sSharedPreferences == null) {
            getSharedPreferences(context);
        }
        return sSharedPreferences.getString(key, defValue);
    }

    /**
     * 移除
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        if (sSharedPreferences == null) {
            getSharedPreferences(context);
        }
        sSharedPreferences.edit().remove(key).apply();
    }
}
