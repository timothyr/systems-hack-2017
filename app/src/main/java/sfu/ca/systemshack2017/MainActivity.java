package sfu.ca.systemshack2017;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sfu.ca.systemshack2017.adapters.EventListItemAdapter;
import sfu.ca.systemshack2017.alarm.AlarmBroadcastReceiver;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import junit.framework.Assert;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener {

    public static GoogleApiClient mGoogleApiClient;
    public static Geocoder geocoder;
    private final static int LOCATION_PERMISSION_REQUEST_CODE = 11;

    private ListView eventListView;
    public static List<Event> eventList = new ArrayList<Event>();
    private ArrayAdapter<Event> eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupServices();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCreateAlarmActivity();

            }
        });

        callAlarmScheduleService();

        //setup event list
        eventListAdapter = new EventListItemAdapter(this, R.layout.alarm_list_item, eventList);
        eventListView = (ListView) findViewById(R.id.eventListView);
        eventListView.setAdapter(eventListAdapter);

    }

    public void setupServices() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();

        geocoder = new Geocoder(this, Locale.CANADA);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("Connection", "Connection Ready");

        Assert.assertTrue(mGoogleApiClient.isConnected());
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            Toast.makeText(this, location.toString(), Toast.LENGTH_LONG);
            Log.d("Location", location.toString());
        } else {
            Log.d("NullLocation", "Location is null");

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PERMISSION Granted
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Location service not enabled", Toast.LENGTH_SHORT).show();
                }
                return;
            default:
                return;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently
        Log.d("Connection failed", "" + result.getErrorCode());
        Toast.makeText(this, "Google Play Service connection failed. " + result.getErrorMessage(), Toast.LENGTH_LONG);
    }

    @Override
    public void onConnectionSuspended(int i) {

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
        startActivityForResult(myIntent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eventListAdapter.notifyDataSetChanged();
    }

    protected void callAlarmScheduleService() {
        Intent alarmServiceIntent = new Intent(this, AlarmBroadcastReceiver.class);
        sendBroadcast(alarmServiceIntent, null);
    }
}
