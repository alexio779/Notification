package com.example.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;




public class MainActivity extends AppCompatActivity {
    public final static String GROUP_KEY = "GK";
    public final static String CHANNEL_ID = "channel_id";

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
                showNewsMessage();
                summaryNotification();
            }
        });
    }


    //--------------------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.S)

    private void showNewsMessage() {

        PendingIntent InterestingIntent=PendingIntent.getActivity(MainActivity.this,0,
                new Intent(MainActivity.this,MainActivity.class),PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .addAction(0, "Не интересно", InterestingIntent)
                .setContentText("text")
                .setContentTitle("title")
                .setGroup(GROUP_KEY);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
        nb.setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_HIGH
                );

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    channel.setAllowBubbles(true);

                notificationManager.createNotificationChannel(channel);
            }
        } else
            nb.setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(0, nb.build());
        notificationManager.notify(1, nb.build());
    }
    //--------------------------------------------------------------------------------------------------
    private void summaryNotification()
    {
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentInfo("user_mail.com")
                .setGroup(GROUP_KEY)
                .setGroupSummary(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(-100, nb.build());
    }
}