package com.example.notifications;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;



public class MainActivity extends AppCompatActivity {
    public final static String GROUP_KEY = "GK";
    public final static String CHANNEL_ID = "channel_id";
    public final static int FIRST_NOTIFY_ID = 1;
    public final static int SECOND_NOTIFY_ID = 2;
    public final static int SUMMARY_NOTIFY_ID = 3;
    NotificationManagerCompat notificationManagerCompat;

    Notification notification, notification2, notification3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View v)
            {
                firstNotification();
                secondNotification();
                summaryNotification();
            }
        });
    }

//--------------------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.S)
    private void firstNotification()
    {
        PendingIntent InterestingIntent=PendingIntent.getActivity(MainActivity.this,0,
                new Intent(MainActivity.this,MainActivity.class),PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .addAction(0, "Не интересно", InterestingIntent)
                .setContentTitle("2")
                .setGroup(GROUP_KEY)
                .setContentInfo("Первое уведомление")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText("text");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "my Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        setContentView(R.layout.activity_main);

        notificationManagerCompat.notify(FIRST_NOTIFY_ID, notification);
    }

//--------------------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.S)
    private void secondNotification()
    {
        PendingIntent InterestingIntent=PendingIntent.getActivity(MainActivity.this,0,
                new Intent(MainActivity.this,MainActivity.class),PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .addAction(0, "Не интересно", InterestingIntent)
                .setContentTitle("1")
                .setGroup(GROUP_KEY)
                .setContentInfo("Второе уведомление")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText("text");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "my Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notification2 = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        setContentView(R.layout.activity_main);

        notificationManagerCompat.notify(SECOND_NOTIFY_ID, notification2);
    }
//--------------------------------------------------------------------------------------------------
    private void summaryNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true);
        notification3 = builder.build();

        notificationManagerCompat.notify(SUMMARY_NOTIFY_ID, notification3);
    }
}