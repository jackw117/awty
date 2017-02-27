package edu.washington.jackw117.awty;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jack on 2/21/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(MainActivity.MESSAGE);
        String number = intent.getStringExtra(MainActivity.NUMBER);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message, null, null);
    }
}
