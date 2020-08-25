package com.vinsofts.sotayvatly10.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.utils.MySharedPreferences;

/**
 * Created by MyPC on 15/04/2017.
 */

public class RateDialog extends DialogFragment implements View.OnClickListener {

    private TextView tvNoRate;
    private TextView tvRateLater;
    private TextView tvRateNow;


    public static RateDialog newInstance() {
        RateDialog rateDialog = new RateDialog();
        rateDialog.setCancelable(false);
        return rateDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_rate, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        tvNoRate = (TextView) view.findViewById(R.id.tvNoRate);
        tvRateLater = (TextView) view.findViewById(R.id.tvRateLater);
        tvRateNow = (TextView) view.findViewById(R.id.tvRateNow);

        tvNoRate.setOnClickListener(this);
        tvRateLater.setOnClickListener(this);
        tvRateNow.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvNoRate:
                MySharedPreferences.saveAppRater(getContext(), 0);
                break;

            case R.id.tvRateLater:
                MySharedPreferences.saveAppRater(getContext(), 1);
                break;

            case R.id.tvRateNow:
                MySharedPreferences.saveAppRater(getContext(), 0);
                openAppOnStore();
                break;
        }
        dismiss();
    }


    private void openAppOnStore() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + Constant.PACKAGE_NAME));
            startActivity(intent);
        } catch (Exception e) { //google play app is not installed
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + Constant.PACKAGE_NAME));
            startActivity(intent);
        }
    }
}
