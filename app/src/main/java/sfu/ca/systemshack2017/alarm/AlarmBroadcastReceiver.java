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
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import sfu.ca.systemshack2017.Alarm;

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
        }

        Intent serviceIntent = new Intent(context, AlarmService.class);
        context.startService(serviceIntent);
        // TO:DO add vibrate, etc
    }
}
