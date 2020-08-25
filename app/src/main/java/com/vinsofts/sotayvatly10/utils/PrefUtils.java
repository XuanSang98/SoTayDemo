package com.vinsofts.sotayvatly10.utils;

import android.content.SharedPreferences;

import com.vinsofts.sotayvatly10.common.Constant;

/**
 * Created by MyPC on 13/04/2017.
 */

public class PrefUtils {

    public static final String PREF_BASE = "PREF_BASE";
    public static final String PREF_VERSION = "PREF_VERSION";

    public static SharedPreferences sharedPref;

    public static String getCurrentVersion() {
        return sharedPref.getString(PREF_VERSION, "");
    }

    public static void setCurrentVersion() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_VERSION, Constant.VERSION_NAME);
        editor.apply();
    }
}
