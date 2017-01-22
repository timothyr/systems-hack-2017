package sfu.ca.systemshack2017;

import android.content.Context;
import android.media.*;
import android.net.Uri;

/**
 * Created by Joe on 1/21/2017.
 */

public class AlarmPlayer {

    static Uri notification;
    static Ringtone r;

    public static void setup(Context context) {
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(context, notification);
    }

    public static void playSound() {
        r.play();
    }

    public static void stopSound() {
        r.stop();
    }
}
