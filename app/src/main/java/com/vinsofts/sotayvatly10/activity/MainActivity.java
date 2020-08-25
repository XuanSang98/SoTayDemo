package com.vinsofts.sotayvatly10.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdView;
import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.fragment.ContentFragment;
import com.vinsofts.sotayvatly10.fragment.FavoriteFragment;
import com.vinsofts.sotayvatly10.fragment.HomeFragment;
import com.vinsofts.sotayvatly10.fragment.SettingFragment;
import com.vinsofts.sotayvatly10.fragment.TipsFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnHome, btnContent, btnFavorite, btnTips, btnSetting;
    private LinearLayout demo;

    private FragmentManager frManager;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        frManager = getSupportFragmentManager();
        Fragment fragment = frManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new ContentFragment();
            frManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        AdView adView = (AdView) findViewById(R.id.adView);
        initAds(adView);
    }




    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v == findViewById(R.id.btnHome)) {
            fragment = new HomeFragment();
        } else if (v == findViewById(R.id.btnContent)) {
            fragment = new ContentFragment();
        } else if (v == findViewById(R.id.btnFavorite)) {
            fragment = new FavoriteFragment();
        } else if (v == findViewById(R.id.btnTips)) {
            fragment = new TipsFragment();
        } else if (v == findViewById(R.id.btnSetting)) {
            fragment = new SettingFragment();
        }


        FragmentTransaction transaction = frManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void initView() {
        btnHome = (Button) findViewById(R.id.btnHome);
        btnContent = (Button) findViewById(R.id.btnContent);
        btnFavorite = (Button) findViewById(R.id.btnFavorite);
        btnTips = (Button) findViewById(R.id.btnTips);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        demo = (LinearLayout) findViewById(R.id.demo);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);

        btnHome.setOnClickListener(this);
        btnContent.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);
        btnTips.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeBackground(demo);

    }



    @Override
    protected void onPause() {
        super.onPause();
    }
}
