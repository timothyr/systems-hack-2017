package sfu.ca.systemshack2017;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                Alarm alarm = new Alarm(setAlarmTime());
                alarm.scheduleAlarm(getApplicationContext());
                //createNewAlarm(setAlarmTime());
                CharSequence text = "Created Alarm!";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Location sfuBurnaby = new Location("");
                sfuBurnaby.setLatitude(49.2780937);
                sfuBurnaby.setLongitude(-122.922072);
                Event event = new Event("", setAlarmTime(), sfuBurnaby);
                MainActivity.eventList.add(event);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addLocation);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       openMapsActivity();

                                   }
                               });



    }

    private void openMapsActivity(){

        Intent mapIntent = new Intent(this, MapsActivity.class);
        Bundle newBundle = new Bundle();
        newBundle.putDouble("Longitude", getIntent().getExtras().getDouble("Longitude"));
        newBundle.putDouble("Latitude", getIntent().getExtras().getDouble("Latitude"));
        mapIntent.putExtras(newBundle);
        startActivity(mapIntent);
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
