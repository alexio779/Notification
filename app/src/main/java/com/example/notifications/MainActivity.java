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

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PendingIntent InterestingIntent=PendingIntent.getActivity(MainActivity.this,0,
                new Intent(MainActivity.this,MainActivity.class),PendingIntent.FLAG_MUTABLE);

        String text = "Текст уведомления";
        String title = "Заголовок уведомления";
        String btn_title = "Не интересно";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .addAction(0, btn_title, InterestingIntent)
                .setContentTitle(title)
                .setGroupSummary(true)
                .setGroup(GROUP_KEY)
                .setContentInfo("123")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(text);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("myCh", "my Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                notificationManagerCompat.notify(1, notification);
                notificationManagerCompat.notify(2, notification);
            }

            public void smt(){

            }

        });
    }

}