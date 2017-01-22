package sfu.ca.systemshack2017.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by timr on 2017-01-21.
 */

public class AlarmService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(this.getClass().getSimpleName(),"onCreate()");
        super.onCreate();

        updateEventScheduler();
    }

    public void updateEventScheduler() {
        int updateFrequencyInMillis = 30 * 1000;

        // the best number
        long id = -999;

        // Package intent
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        alarmIntent.putExtra("updateevents", id);

        //TO:DO different requestcode for each alarm
        int requestCode = -999;

        // Create new alarm intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                requestCode,
                alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), updateFrequencyInMillis, pendingIntent);

        Log.d("Alarm Event Scheduler", "Set for every " + updateFrequencyInMillis/1000 + " seconds");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getSimpleName(), "onStartCommand()");



        return START_NOT_STICKY;
    }


}
