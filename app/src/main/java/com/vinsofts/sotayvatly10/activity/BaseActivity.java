package com.vinsofts.sotayvatly10.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.vinsofts.sotayvatly10.R;

/**
 * Created by MyPC on 29/03/2017.
 */

public class BaseActivity extends AppCompatActivity{

    private SharedPreferences sharedPref;
    protected InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.inter));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    protected void changeBackground(View view) {
        sharedPref = getSharedPreferences("background", Context.MODE_PRIVATE);
        int dem = sharedPref.getInt("check", -1);
        if (dem == 0) {
            changeBackgroundStill(view);
        } else if (dem == 1) {
            changebackgroundYourOwn(view);
        }
    }


    public void changeBackgroundStill(View view) {
        int bg = sharedPref.getInt("background_resource", android.R.color.white);
        view.setBackgroundResource(bg);
    }

    public void changebackgroundYourOwn(View view) {
        String fondo = sharedPref.getString("background_path", "vacio");
        if (!fondo.equals("vacio")) {
            Bitmap bitmap = BitmapFactory.decodeFile(fondo);
            BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
            view.setBackgroundDrawable(bd);

        }
    }

    protected void initAds(final AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });




    }



}
