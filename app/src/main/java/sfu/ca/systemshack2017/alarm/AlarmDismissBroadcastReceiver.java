package sfu.ca.systemshack2017.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import sfu.ca.systemshack2017.AlarmPlayer;

/**
 * Created by brandon on 1/21/2017.
 */

public class AlarmDismissBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmPlayer.stopSound();

        Log.d("ALARM", "DISMISS");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(123);
    }
}
