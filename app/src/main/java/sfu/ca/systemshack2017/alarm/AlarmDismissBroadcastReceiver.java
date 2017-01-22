package sfu.ca.systemshack2017.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sfu.ca.systemshack2017.AlarmPlayer;

/**
 * Created by brandon on 1/21/2017.
 */

public class AlarmDismissBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmPlayer.stopSound();
    }
}
