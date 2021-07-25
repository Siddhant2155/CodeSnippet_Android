package com.example.a20_notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showNotification(view: View) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            /// Channel are use to categories the notification types
            val channel = NotificationChannel("Channel ID", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, "Channel ID")
        notification.setContentTitle("Title of the notification")
        notification.setContentText("This is notification body")
        notification.setSmallIcon(R.drawable.ic_launcher_background)
        notification.setAutoCancel(true)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(2, notification.build())
    }
}