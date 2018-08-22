package com.example.myfirstapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.app.Notification.VISIBILITY_PUBLIC;
import static com.example.myfirstapp.MyNotificationChannel.CHANNEL_1;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Setting notification channel
        String id = "my_channel_01";
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);

        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel mChannel = new NotificationChannel(id, name,importance);
        mChannel.setDescription(description);

        mNotificationManager.createNotificationChannel(mChannel);

        //Setting notification
        int notifyID = 1;

        Notification notification = new Notification.Builder(MainActivity.this, mChannel.getId())
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.notification_icon)
                .build();

        mNotificationManager.notify(notifyID, notification);

    }

    public void startAlarm(){

        //AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent activityIntent = new Intent(this, DisplayMessageActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, MyNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Notification")
                .setContentText("Hi!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setVisibility(VISIBILITY_PUBLIC)
                .setColor(Color.BLUE)
                .build();

        //notificationManager.notify(1, notification);

        //alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000, pendingIntent);
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
