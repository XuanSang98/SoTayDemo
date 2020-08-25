package com.vinsofts.sotayvatly10.application;

import android.app.Application;

import com.vinsofts.sotayvatly10.BuildConfig;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.utils.PrefUtils;

import java.io.File;

import static com.vinsofts.sotayvatly10.utils.PrefUtils.PREF_BASE;

/**
 * Created by MyPC on 13/04/2017.
 */

public class HandbookApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        PrefUtils.sharedPref = getSharedPreferences(PREF_BASE, MODE_PRIVATE);

        //Update version database
        if (!PrefUtils.getCurrentVersion().equals(Constant.VERSION_NAME)) {
            File fileDatabase = new File(Constant.PATH + "/" + BuildConfig.DATABASE_NAME);
            if(fileDatabase.exists()) fileDatabase.delete();

            PrefUtils.setCurrentVersion();
        }

    }
}
