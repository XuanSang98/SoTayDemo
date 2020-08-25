package com.vinsofts.sotayvatly10.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by HongChien on 26/04/2016.
 */
public class ListenerDialog implements TimePickerDialog.OnTimeSetListener{
    private Context mContext;
    private Calendar calNow;
    private Calendar calSet;

    public ListenerDialog(Context mContext, Calendar calNow, Calendar calSet) {
        this.mContext = mContext;
        this.calNow = calNow;
        this.calSet = calSet;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calNow = Calendar.getInstance();
        calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if (calSet.compareTo(calNow) <= 0) {
            //Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }

        setAlarm();
    }

    public void setAlarm() {
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), 24*60*60*1000, pendingIntent);
        Log.d("Chien",calSet.getTimeInMillis()+"");

    }

    public void cancelAlarm() {
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mContext, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(alarmIntent);
    }
}
