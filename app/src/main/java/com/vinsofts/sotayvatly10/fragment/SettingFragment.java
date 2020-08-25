package com.vinsofts.sotayvatly10.fragment;

import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.activity.BackgroundActivity;
import com.vinsofts.sotayvatly10.activity.PrimacyActivity;
import com.vinsofts.sotayvatly10.common.AlarmReceiver;
import com.vinsofts.sotayvatly10.common.ListenerDialog;

import java.util.Calendar;

/**
 * Created by Administrator on 20/04/2016.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RelativeLayout reActivity, reEmail, rePrivacy, reMore;
    private SharedPreferences sharedPref;
    private TimePickerDialog timePickerDialog;
    private ListenerDialog onTimeSetListener;
    private Switch reSwitch;
    private Intent intent;
    private boolean isCheck = false;
    private Calendar calendar;
    public Calendar calSet;
    private RelativeLayout reSettingFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, viewGroup, false);

        reSettingFragment = (RelativeLayout) view.findViewById(R.id.reSettingFragment);
        reActivity = (RelativeLayout) view.findViewById(R.id.reActivity);
        reSwitch = (Switch) view.findViewById(R.id.reSwitch);
        reEmail = (RelativeLayout) view.findViewById(R.id.reEmail);
        rePrivacy = (RelativeLayout) view.findViewById(R.id.rePrivacy);
        reMore = (RelativeLayout) view.findViewById(R.id.reMore);

        reSwitch.setOnCheckedChangeListener(this);
        reActivity.setOnClickListener(this);
        reEmail.setOnClickListener(this);
        reMore.setOnClickListener(this);
        rePrivacy.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 21) {
            reSettingFragment.setPadding(dp2px(20), getStatusBarHeight(), dp2px(20), 0);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reActivity:
                intent = new Intent(getActivity(), BackgroundActivity.class);
                startActivity(intent);
                break;
            case R.id.reEmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "baitap123.com@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;
            case R.id.rePrivacy:
                intent = new Intent(getActivity(), PrimacyActivity.class);
                startActivity(intent);
                break;

            case R.id.reMore:
                openAppOnStore();
                break;
        }
    }

    private void openTimePickerDialog(final boolean is24r) {
        calendar = Calendar.getInstance();
        calSet = (Calendar) calendar.clone();
        onTimeSetListener = new ListenerDialog(getActivity(), calendar, calSet);
        timePickerDialog = new TimePickerDialog(
                getActivity(),
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");
        timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                NotificationEventReceiver.setupAlarm(getActivity().getApplicationContext());
//                stateBroadcastReceiver(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);


                onTimeSetListener.setAlarm();
//
                sharedPref = getActivity().getSharedPreferences("my_prefs",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("check", true);
                editor.apply();
                isCheck = true;
            }
        });
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Luu trang thai vao shared
                //set check false;
                sharedPref = getActivity().getSharedPreferences("my_prefs",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("check", false);
                editor.apply();
                reSwitch.setChecked(false);
                isCheck = false;

            }
        });

        timePickerDialog.show();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (!isCheck) {
                isCheck = true;
                openTimePickerDialog(true);
            }

        } else {
            isCheck = false;
            calendar = Calendar.getInstance();
            calSet = (Calendar) calendar.clone();
            onTimeSetListener = new ListenerDialog(getActivity(), calendar,calSet);
            onTimeSetListener.cancelAlarm();
//            stateBroadcastReceiver(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
//            NotificationEventReceiver.cancelAlarm(getActivity().getApplicationContext());
            reSwitch.setChecked(false);
        }
    }

    public void stateBroadcastReceiver(int state) {
        ComponentName receiver = new ComponentName(getActivity(), AlarmReceiver.class);
        PackageManager pm = getActivity().getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                state,
                PackageManager.DONT_KILL_APP);

    }

    @Override
    public void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadState();
    }

    public void saveState() {
        SharedPreferences pre = getActivity().getSharedPreferences("my_switch", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        boolean bchk = reSwitch.isChecked();
        if (!bchk) {
            editor.clear();
        } else {
            editor.putBoolean("checked", bchk);
            editor.putBoolean("1", isCheck);
        }
        editor.apply();
    }

    public void loadState() {
        SharedPreferences pre = getActivity().getSharedPreferences("my_switch", Context.MODE_PRIVATE);
        boolean bchk = pre.getBoolean("checked", false);
        isCheck = pre.getBoolean("1", false);
        reSwitch.setChecked(bchk);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getActivity().getResources().getDisplayMetrics());
    }
}
