package edu.washington.jackw117.awty;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE = "message";
    public static final String NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alarm alarmClass = new Alarm(this);
        final AlarmManager alarmManager = alarmClass.getAlarm();
        final Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);


        final Button start = (Button) findViewById(R.id.startButton);
        final EditText message = (EditText) findViewById(R.id.editMessage);
        final EditText number = (EditText) findViewById(R.id.editNumber);
        final EditText minutes = (EditText) findViewById(R.id.editMinute);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 1);
            }
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputMinutes = minutes.getText().toString();
                String inputMessage = message.getText().toString();
                String inputNumber = number.getText().toString();
                if (String.valueOf(inputMinutes).contains("0")) {
                    Toast.makeText(MainActivity.this, "Minutes between each message cannot have any zeros", Toast.LENGTH_SHORT).show();
                } else if (inputMinutes.equals("") || inputMessage.equals("") || inputNumber.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter values for each input", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(MESSAGE, inputMessage);
                    intent.putExtra(NUMBER, inputNumber);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    if (start.getText().equals("Start")) {
                        start.setText("Stop");
                        int milliseconds = Integer.parseInt(inputMinutes) * 60000;
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), milliseconds, pendingIntent);
                    } else {
                        alarmManager.cancel(pendingIntent);
                        start.setText("Start");
                    }
                }
            }
        });
    }
}
