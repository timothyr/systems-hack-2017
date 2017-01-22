package sfu.ca.systemshack2017;

import android.content.res.Resources;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.google.maps.internal.ExceptionResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Joe on 1/21/2017.
 */

public class DistanceCalculator {

    static private String apiKey = "AIzaSyBQCbNu8HlhJHUe_Rjh3HlIiLKdc54bFlo";

    public static String getResultFromGoogle(Event event) {

        Location location = LocationServices.FusedLocationApi.getLastLocation(MainActivity.mGoogleApiClient);

        String url = getGoogleMapUrl(location, event.getLocation(), TravelOption.TRANSIT, event.getCalendar());
        DistanceThread thread = new DistanceThread(url);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            return "";
        }

        return thread.result;
    }

    public static Calendar getDepartureTime(Event event) {
        try {
            String jsonString = getResultFromGoogle(event);

            JSONObject json = new JSONObject(jsonString);
            JSONObject routes = (JSONObject) json.getJSONArray("routes").get(0);
            JSONObject legs = (JSONObject) routes.getJSONArray("legs").get(0);
            JSONObject departureTime = (JSONObject) legs.get("departure_time");

            String time = departureTime.getString("text");
            Log.d("Distance Calculator", time);
            SimpleDateFormat df = new SimpleDateFormat("h:mma");
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(time));
            return c;

        } catch (Exception e) {
            Log.d("Distance Calculator", e.getMessage());
            return null;
        }
    }

    public static String getGoogleMapUrl(Location origin, Location destination, TravelOption travelOption, Calendar calendar) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?";

        url += "origin=" + origin.getLatitude() + "," + origin.getLongitude() + "&";
        url += "destination=" + destination.getLatitude() + "," + destination.getLongitude() + "&";
        url += "key=" + apiKey + "&";

        url += "mode=";
        switch (travelOption) {
            case DRIVING:
                url += "driving";
                break;
            case TRANSIT:
                url += "transit";
                break;
        }

        url += "&arrival_time=" + calendar.getTimeInMillis() / 1000;

        Log.d("GOOGLE DEBUG", url);
        return url;
    }

}
