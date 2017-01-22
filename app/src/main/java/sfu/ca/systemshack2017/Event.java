package sfu.ca.systemshack2017;

import android.location.Location;

import java.util.Calendar;

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
}
