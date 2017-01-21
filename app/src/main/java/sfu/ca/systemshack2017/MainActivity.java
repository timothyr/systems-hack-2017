package sfu.ca.systemshack2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sfu.ca.systemshack2017.adapters.EventListItemAdapter;
import sfu.ca.systemshack2017.alarm.AlarmBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private ListView eventListView;
    private List<Event> eventList = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCreateAlarmActivity();

            }
        });

        callAlarmScheduleService();

        //setup event list
        ArrayAdapter<Event> eventListAdapter = new EventListItemAdapter(this, R.layout.alarm_list_item, eventList);
        eventListView = (ListView) findViewById(R.id.eventListView);
        eventListView.setAdapter(eventListAdapter);

        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
        eventList.add(new Event());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void settingsClick(MenuItem menuItem) {
        //Toast.makeText(this, "SETTINGS", Toast.LENGTH_LONG).show();
        callAlarmScheduleService();
    }

    private void openCreateAlarmActivity() {
        Intent myIntent = new Intent(this, CreateAlarm.class);
        startActivity(myIntent);
    }

    protected void callAlarmScheduleService() {
        Intent alarmServiceIntent = new Intent(this, AlarmBroadcastReceiver.class);
        sendBroadcast(alarmServiceIntent, null);
    }
}
