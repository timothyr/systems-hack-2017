package sfu.ca.systemshack2017;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sfu.ca.systemshack2017.alarm.AlarmBroadcastReceiver;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by timr on 2017-01-21.
 */

public class Alarm implements Serializable {

    private long id;
    private Boolean active = true;
    private Calendar time = Calendar.getInstance();
    private Boolean vibrate = true;
    private String name = "Default";

    public Alarm() {

    }

    /**
     *
     * @param calendar Time as Calendar object
     */
    public Alarm(Calendar calendar) {
        this.time = calendar;
    }

    /**
     *
     * @param time Time in millis
     */
    public Alarm(long time) {
        this.time.setTimeInMillis(time);
    }


    public void scheduleAlarm(Context context) {
        // id is equal to epoch time
        id = getAlarmTime();

        // Package intent
        Intent alarmIntent = new Intent(context, AlarmBroadcastReceiver.class);
        alarmIntent.putExtra("alarm", this);
        alarmIntent.putExtra("id", id);

        //TO:DO different requestcode for each alarm
        int requestCode = 0;

        // Create new alarm intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
                //PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, getAlarmTime(), pendingIntent);
        String myFormat = "yyyy.MM.dd G 'at' K:mm:ss z"; //In which you need put here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.CANADA);
        String logMsg = "Alarm set for " + simpleDateFormat.format(getAlarmCalendar().getTime());
        Log.d("Alarm class", logMsg);
        Toast.makeText(context, logMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @return Alarm is active True/False
     */
    public Boolean getAlarmActive() {
        return active;
    }

    /**
     *
     * @param active Set the alarm to active or not
     */
    public void setAlarmActive(Boolean active) {
        this.active = active;
    }

    /**
     *
     * @return Alarm time as Calendar object
     */
    public Calendar getAlarmCalendar() {
        return time;
    }

    /**
     *
     * @return Alarm time in milliseconds (epoch)
     */
    public long getAlarmTime() {
        return time.getTimeInMillis();
    }

    /**
     *
     * @param time The time to alert user
     */
    public void setAlarmTime(Calendar time) {
        this.time = time;
    }


    /**
     *
     * @return Vibrate enabled/disabled
     */
    public Boolean getVibrate() {
        return vibrate;
    }

    /**
     *
     * @param vibrate enable/disable vibrate
     */
    public void setVibrate(Boolean vibrate) {
        this.vibrate = vibrate;
    }

    /**
     *
     * @return Name of alarm
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name Name of alarm
     */
    public void setName(String name) {
        this.name = name;
    }
}
