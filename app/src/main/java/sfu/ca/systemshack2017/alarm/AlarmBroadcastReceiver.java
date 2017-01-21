package sfu.ca.systemshack2017.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public AlarmBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Alarm Service", "onReceive()");
        //Toast.makeText(context, "Alarm t r i g g e r e d", Toast.LENGTH_LONG).show();

        Intent serviceIntent = new Intent(context, AlarmService.class);
        context.startService(serviceIntent);
        // TO:DO add vibrate, etc
    }
}
