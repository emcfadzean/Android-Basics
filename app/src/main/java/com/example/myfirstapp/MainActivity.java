package com.example.myfirstapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

import static android.app.Notification.VISIBILITY_PUBLIC;
import static com.example.myfirstapp.MyNotificationChannel.CHANNEL_1;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    //NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAlarm();
    }

    public void startAlarm(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 17);
        c.set(Calendar.MINUTE, 11);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        Log.i("Time", ""+c.toString());

        /*mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent activityIntent = new Intent(this, DisplayMessageActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, MyNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("MyFirstApp")
                .setContentText("G'Day")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOnlyAlertOnce(true)
                .setVisibility(VISIBILITY_PUBLIC)
                .setColor(Color.BLUE)
                .build();

        long current = SystemClock.currentThreadTimeMillis() + 10000;
        while(true) {
            if (current == SystemClock.currentThreadTimeMillis()) {
                mNotificationManager.notify(1, notification);
                break;
            }
        }*/

        //alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000, );
        //Log.i("NotificationReciever","Status "+NotificationReceiver.received);
        //if(NotificationReceiver.received)Log.i("Called","Here.");
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
