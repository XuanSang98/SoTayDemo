package com.vinsofts.sotayvatly10.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.fragment.StillFragment;
import com.vinsofts.sotayvatly10.fragment.YourOwnFragment;


/**
 * Created by Administrator on 22/04/2016.
 */
public class BackgroundActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rbtStill, rbtYour;
    private FragmentManager manager;
    private ImageButton btnBackBackground;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        rbtStill = (RadioButton) findViewById(R.id.btnStill);
        rbtYour = (RadioButton) findViewById(R.id.btnYourOwn);
        btnBackBackground = (ImageButton) findViewById(R.id.btnBackBackground);

        btnBackBackground.setOnClickListener(this);
        rbtStill.setOnClickListener(this);
        rbtYour.setOnClickListener(this);


        manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.input);
        if (fragment == null) {
            fragment = new StillFragment();
            manager.beginTransaction().add(R.id.input,fragment).commit();
        }

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btnStill:
                fragment = new StillFragment();
                transaction.replace(R.id.input, fragment);
                transaction.commit();
                break;
            case R.id.btnYourOwn:
                fragment = new YourOwnFragment();
                transaction.replace(R.id.input, fragment);
                transaction.commit();
                break;
            case R.id.btnBackBackground:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
