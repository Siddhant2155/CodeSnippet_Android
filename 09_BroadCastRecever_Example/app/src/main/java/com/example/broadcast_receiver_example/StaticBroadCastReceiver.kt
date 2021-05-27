package com.example.broadcast_receiver_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class StaticBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("Siddhant", "LOGGEDDDDD")
        Toast.makeText(context, "Phone Unlocked", Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}