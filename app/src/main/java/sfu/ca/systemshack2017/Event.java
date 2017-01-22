package sfu.ca.systemshack2017;

import android.location.Address;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

/**
 * Created by brandon on 1/21/2017.
 */
public class Event {

    private Alarm alarm;
    private String name;
    private Calendar calendar;
    private LatLng latLng;


    public Event(String name, Calendar calendar, LatLng latLng) {
        this.name = name;
        this.calendar = calendar;
        this.latLng = latLng;
        createAlarm();
        refreshAlarmTime();
    }

    public void refreshAlarmTime() {
        alarm.setAlarmTime(DistanceCalculator.getDepartureTime(this));
    }

    private void createAlarm() {
        this.alarm = new Alarm();
        alarm.setAlarmTime(calendar);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public String getLocationName() {
        try {
            List<Address> addresses = MainActivity.geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            return addresses.get(0).getAddressLine(0);
        } catch (Exception ex) {

        }
        return "";

    }
}
