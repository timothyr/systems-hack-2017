package sfu.ca.systemshack2017;

import android.location.Location;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Joe on 1/21/2017.
 */

public class DistanceCalculator {

    public static String test() {

        DistanceThread thread = new DistanceThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            return "";
        }

        return thread.result;
    }

    public String getGoogleMapUrl(Location origin, Location destination, TravelOption travelOption) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?";

        url += "origin=" + origin.getLatitude() + "," + origin.getLongitude() + "&";
        url += "destination=" + destination.getLatitude() + "," + destination.getLongitude() + "&";
        url += "key=" + R.string.google_map_api_key;

        url += "mode=";
        switch (travelOption) {
            case DRIVING:
                url += "driving";
            case TRANSIT:
                url += "transit";
        }

        return url;
    }

}
