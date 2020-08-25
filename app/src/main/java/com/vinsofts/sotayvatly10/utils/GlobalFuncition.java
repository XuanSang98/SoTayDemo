package com.vinsofts.sotayvatly10.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by Ha on 3/14/2017.
 */

public class GlobalFuncition {

    private static final String KEY_CREATE_DATEBASE = "KEY_CREATE_DATEBASE";

    public static boolean isCreatDatabase(Context context) {
        return MySharedPreferences.getBooleanValue(context, KEY_CREATE_DATEBASE);
    }

    public static void setCreatDatabase(Context context, boolean values) {
        MySharedPreferences.putBooleanValue(context, KEY_CREATE_DATEBASE, values);
    }

    private static final String KEY_FONT_TEXT = "KEY_FONT_TEXT";

    public static String getFontText(Context context) {
        return MySharedPreferences.getStringValue(context, KEY_FONT_TEXT);
    }

    public static void setFontText(Context context, String font) {
        MySharedPreferences.putStringValue(context, KEY_FONT_TEXT, font);
    }

    private static final String KEY_SIZE_TEXT = "KEY_SIZE_TEXT";

    public static int getSizeText(Context context) {
        return MySharedPreferences.getIntValue(context, KEY_SIZE_TEXT, 8);
    }

    public static void setSizeText(Context context, int size) {
        MySharedPreferences.putIntValue(context, KEY_SIZE_TEXT, size);
    }

    private static final String KEY_BRIGHTNESS = "KEY_BRIGHTNESS";

    public static int getBrightness(Context context) {
        return MySharedPreferences.getIntValue(context, KEY_BRIGHTNESS, -1);
    }

    public static void setBrightness(Context context, int brightness) {
        MySharedPreferences.putIntValue(context, KEY_BRIGHTNESS, brightness);
    }

    private static final String KEY_BRIGHTNESS_DEFAULT = "KEY_BRIGHTNESS_DEFAULT";

    public static boolean getBrightnessDefault(Context context) {
        return MySharedPreferences.getBooleanValue(context, KEY_BRIGHTNESS_DEFAULT, true);
    }

    public static void setBrightnessDefault(Context context, boolean values) {
        MySharedPreferences.putBooleanValue(context, KEY_BRIGHTNESS_DEFAULT, values);
    }


    private static final String KEY_CATE_LAST_READ = "KEY_CATE_LAST_READ";

    public static int getCateLastRead(Context context) {
        return MySharedPreferences.getIntValue(context, KEY_CATE_LAST_READ, -1);

    }

    public static void setCateLastRead(Context context, int cateId) {
        MySharedPreferences.putIntValue(context, KEY_CATE_LAST_READ, cateId);

    }

    private static final String KEY_POSITION_LAST_READ = "KEY_POSITION_LAST_READ";

    public static int gePositionLastRead(Context context) {
        return MySharedPreferences.getIntValue(context, KEY_POSITION_LAST_READ, -1);

    }

    public static void setPositionLastRead(Context context, int position) {
        MySharedPreferences.putIntValue(context, KEY_POSITION_LAST_READ, position);

    }

    private static final String KEY_THEME = "KEY_THEME";


    private static final String KEY_LOCK_SCREEN = "KEY_LOCK_SCREEN";


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null) {
            return false;
        }
        if (!i.isConnected()) {
            return false;
        }
        if (!i.isAvailable()) {
            return false;
        }
        return true;
    }


}
