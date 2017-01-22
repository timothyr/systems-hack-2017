package sfu.ca.systemshack2017;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static sfu.ca.systemshack2017.MainActivity.geocoder;

/**
 * Created by brandon on 1/21/2017.
 */
public class Event {

    private Alarm alarm;
    private String name;
    private Calendar calendar;
    private Location location;

    public Event(String name, Calendar calendar, Location location) {
        this.name = name;
        this.calendar = calendar;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public String getLocationName() {
        try {
            List<Address> addresses = MainActivity.geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            return addresses.get(0).getAddressLine(0);
        } catch (Exception ex) {

        }
        return "";

    }
}
