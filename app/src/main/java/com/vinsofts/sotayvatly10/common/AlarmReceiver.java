package com.vinsofts.sotayvatly10.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.activity.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Time Up", "5second has passed", "Alert");
        Log.d("Chien", "Run alarm manager");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert) {
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context, 2, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationCompat.Builder
                mbuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_favorite_detail_selected)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mbuilder.setContentIntent(pendingNotificationIntent);
        mbuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mbuilder.setAutoCancel(true);
        final NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mbuilder.build());

    }


}
