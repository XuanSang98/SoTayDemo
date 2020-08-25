package com.vinsofts.sotayvatly10.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;
import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.fragment.ContentFragment;

/**
 * Created by MyPC on 14/04/2017.
 */

public class LessonActivity extends BaseActivity {


    private ViewGroup vRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        vRoot = (ViewGroup) findViewById(R.id.vRoot);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        Bundle bundle = getIntent().getExtras();

        if (fragment == null) {

            ContentFragment contentFragment = new ContentFragment();

            if (bundle != null && bundle.containsKey(Constant.BUNDLE_CAT_ID)) {
                contentFragment.setArguments(bundle);
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, contentFragment);
            transaction.commit();
        }

        AdView adView = (AdView) findViewById(R.id.adView);
        initAds(adView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeBackground(vRoot);
    }


}
