package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.channels.NotYetConnectedException;
public class MainActivity extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.firstbn);

       NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       // for oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                    new NotificationChannel("first", "default", NotificationManager.IMPORTANCE_DEFAULT)
            );
        }

        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NotificationCompat.Builder nc = new NotificationCompat.Builder(MainActivity.this, "first");
                        nc.setContentText("Test 1");
                        nc.setContentTitle("Notification Example");
                        nc.setSmallIcon(R.drawable.ic_launcher_background);
                        nc.setPriority(NotificationCompat.PRIORITY_HIGH);
                        Notification n = nc.build();

                        nm.notify(1, n);
                    }
                }
        );

    }
}