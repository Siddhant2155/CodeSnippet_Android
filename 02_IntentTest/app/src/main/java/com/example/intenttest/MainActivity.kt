package com.example.intenttest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        button.setOnClickListener(
//            View.OnClickListener {
//                Toast.makeText(applicationContext, "this is a test", Toast.LENGTH_LONG).show()
//            }
//        )
        button.setOnClickListener(
            View.OnClickListener {
                createAlarm("Alam Test", 1,24)
            }
        )
            button2.setOnClickListener(
                View.OnClickListener {
                    composeEmail("abc@xyz.com", "This is a test")
                }
            )
    }

    // Alarm Intent
    fun createAlarm(message: String, hour: Int, minutes: Int) {
        /*
        For setting alarm intent you need to add permission in manifest file.

        <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />

         */
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeEmail(addresses:String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}