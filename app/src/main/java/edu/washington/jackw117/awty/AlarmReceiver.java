package edu.washington.jackw117.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jack on 2/21/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(MainActivity.MESSAGE);
        String number = intent.getStringExtra(MainActivity.NUMBER);
        String pnumber = number;
        if (number.length() == 10) {
            pnumber = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6);
        } else if (number.length() == 7) {
            pnumber = number.substring(0, 3) + "-" + number.substring(3);
        }
        Toast.makeText(context, "Texting " + pnumber + ": "  + message,
                Toast.LENGTH_SHORT).show();
    }
}
