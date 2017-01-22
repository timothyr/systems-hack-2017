package sfu.ca.systemshack2017.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import sfu.ca.systemshack2017.Alarm;
import sfu.ca.systemshack2017.AlarmPlayer;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public AlarmBroadcastReceiver() {
    }

    private EventScheduler eventscheduler;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("AlarmBroadcastReceiver", "onReceive()");
        //Toast.makeText(context, "Alarm t r i g g e r e d", Toast.LENGTH_LONG).show();


        if(intent.hasExtra("updateevents")) {
            eventscheduler.updateAllEvents();
        }

        if(intent.hasExtra("id")) {
            Log.d("Alarm received", "id = " + intent.getLongExtra("id", -1));

            Intent dismiss = new Intent(context, AlarmDismissBroadcastReceiver.class);
            PendingIntent pendingDismissIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, dismiss, PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setContentTitle("Alarm notification")
                            .setContentText("dismiss")
                            .setSmallIcon(android.R.drawable.arrow_down_float)
                            .setDeleteIntent(pendingDismissIntent)
                            .setContentIntent(pendingDismissIntent);
////                    .setAutoCancel(true)
//                    .setOngoing(false);

            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(123, mBuilder.build());

            AlarmPlayer.playSound();
        }

        Intent serviceIntent = new Intent(context, AlarmService.class);
        context.startService(serviceIntent);
        // TO:DO add vibrate, etc
    }
}
