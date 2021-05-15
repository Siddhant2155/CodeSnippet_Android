package com.example.whatsapp_opener

import android.content.Intent
import android.content.Intent.ACTION_PROCESS_TEXT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number: String = "0"

        if (intent.action == ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if (number.matches("-?\\d+(\\.\\d+)?".toRegex()) ) {
            openWhatsApp(number.trim())
        } else {
            Toast.makeText(this, "Invalid Number", Toast.LENGTH_LONG).show()
        }

    }

    fun openWhatsApp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        Log.d("TEST", number)
        val data: String = if(number[0] == '+') {
            number.substring(1)
        } else if (number.length == 10) {
            "91" + number
        } else {
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        // Check If whatsapp exist or not
        if(packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Whatsapp not installed!!", Toast.LENGTH_SHORT)
        }
        finish()
    }
}