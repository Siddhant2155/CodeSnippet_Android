package com.example.broadcast_receiver_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbr = DynamicBroadCastReceiver()
        val iFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(dbr, iFilter)
    }


    inner class DynamicBroadCastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) return

            if (intent.action == Intent.ACTION_POWER_CONNECTED) {
                Toast.makeText(
                    context,
                    "CHARGER CONNECTED",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (intent.action == Intent.ACTION_POWER_DISCONNECTED) {
                Toast.makeText(
                    context,
                    "CHARGER DISCONNECTED",
                    Toast.LENGTH_SHORT
                ).show()
            }
            context.run { startActivity(Intent(this, MainActivity::class.java)) }
        }
    }
}