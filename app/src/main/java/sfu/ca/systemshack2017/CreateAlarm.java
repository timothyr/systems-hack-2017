package sfu.ca.systemshack2017;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import sfu.ca.systemshack2017.alarm.AlarmBroadcastReceiver;

public class CreateAlarm extends AppCompatActivity {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    SetDate fromDate;
    SetTime fromTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        EditText editTextFromDate = (EditText) findViewById(R.id.dateText);
        fromDate = new SetDate(editTextFromDate, this);
        EditText editTextFromTime = (EditText) findViewById(R.id.timeText);
        fromTime = new SetTime(editTextFromTime, this);

        Button createAlarm = (Button) findViewById(R.id.createButton);
        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createNewAlarm(setAlarmTime());
                CharSequence text = "Created Alarm!";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

        createTestAlarm(12,26);

    }

    private void createTestAlarm(final int hour, final int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        createNewAlarm(calendar.getTimeInMillis());
    }

    private void createNewAlarm(final long time) {

        intent = new Intent(this, AlarmBroadcastReceiver.class);

        //TO:DO different requestcode for each alarm
        int requestCode = 0;
        // Create new alarm intent
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(),
                requestCode,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        Toast.makeText(this, "Alarm will vibrate at time specified",
                Toast.LENGTH_SHORT).show();

    }

    private Calendar setAlarmTime(){

        Calendar alarmCalendar = Calendar.getInstance();

        Calendar date = fromDate.getCalendar();
        Calendar time = fromTime.getCalendar();

        alarmCalendar.set(Calendar.YEAR, date.get(Calendar.YEAR));
        alarmCalendar.set(Calendar.MONTH, date.get(Calendar.MONTH));
        alarmCalendar.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
        alarmCalendar.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        alarmCalendar.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        alarmCalendar.set(Calendar.SECOND, 0);
        alarmCalendar.set(Calendar.MILLISECOND, 0);

        return alarmCalendar;
    }
}
