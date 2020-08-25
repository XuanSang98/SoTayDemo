package com.vinsofts.sotayvatly10.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.vinsofts.sotayvatly10.common.Constant;

public class MySharedPreferences {

    private static final String PREF_BASE = "PREF_BASE";

    /**
     * Save an integer to MySharedPreferences
     *
     * @param key
     * @param n
     */
    public static void putIntValue(Context context, String key, int n) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, n);
        editor.apply();
    }

    /**
     * Read an integer to MySharedPreferences
     *
     * @param key
     * @return
     */
    public static int getIntValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        return pref.getInt(key, 0);
    }

    public static int getIntValue(Context context, String key, int defaultValues) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        return pref.getInt(key, defaultValues);
    }

    /**
     * Save an string to MySharedPreferences
     *
     * @param key
     * @param s
     */
    public static void putStringValue(Context context, String key, String s) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, s);
        editor.apply();
    }

    /**
     * Read an string to MySharedPreferences
     *
     * @param key
     * @return
     */
    public static String getStringValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }


    /**
     * Save an boolean to MySharedPreferences
     *
     * @param key
     * @params
     */
    public static void putBooleanValue(Context context, String key, Boolean b) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }

    /**
     * Read an boolean to MySharedPreferences
     *
     * @param key
     * @return
     */
    public static boolean getBooleanValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }
    public static boolean getBooleanValue(Context context, String key, boolean defaultValues) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_BASE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValues);
    }


    /**
        @param type 0: không xuất hiện nữa
                    1: tiếp tục xuất hiện
     * */
    public static void saveAppRater(Context context, int type) {
        SharedPreferences pref = context.getSharedPreferences(PREF_BASE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Constant.PREF_RATE_APP, type);
        editor.putLong(Constant.PREF_TIME_RATE, System.currentTimeMillis());
        editor.apply();
    }


    public static int getTypeRate(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_BASE, Context.MODE_PRIVATE);
        return pref.getInt(Constant.PREF_RATE_APP, 1);
    }

    public static long getTimeRate(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_BASE, Context.MODE_PRIVATE);
        return pref.getLong(Constant.PREF_TIME_RATE, 0);
    }
}
