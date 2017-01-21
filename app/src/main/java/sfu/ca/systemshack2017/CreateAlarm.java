package sfu.ca.systemshack2017;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import sfu.ca.systemshack2017.alarm.AlarmBroadcastReceiver;

public class CreateAlarm extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        EditText editTextFromDate = (EditText) findViewById(R.id.dateText);
        SetDate fromDate = new SetDate(editTextFromDate, this);
        EditText editTextFromTime = (EditText) findViewById(R.id.timeText);
        SetTime fromTime = new SetTime(editTextFromTime, this);

        createTestAlarm(2,40);

    }



    private void createTestAlarm(final int hour, final int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + 10000);
        //calendar.set(Calendar.HOUR_OF_DAY, hour);
        //calendar.set(Calendar.MINUTE, minute);
        //calendar.set(Calendar.SECOND, 0);

        createNewAlarm(calendar.getTimeInMillis());
    }

    private void createNewAlarm(final long time) {

        Alarm alarm = new Alarm(time);
        alarm.scheduleAlarm(getApplicationContext());

    }
}
