package sfu.ca.systemshack2017;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Joe on 1/21/2017.
 */

public class DistanceThread extends Thread {

    public String result = "";

    @Override
    public void run() {

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood4&key=AIzaSyBQCbNu8HlhJHUe_Rjh3HlIiLKdc54bFlo");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStreamReader inputReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("DC", ex.getMessage() == null ? "NULL" : ex.getMessage());
        }

    }
}
