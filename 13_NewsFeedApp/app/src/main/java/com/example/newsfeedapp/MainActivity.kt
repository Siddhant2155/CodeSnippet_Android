package com.example.newsfeedapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URI

class MainActivity : AppCompatActivity(), NewsTransfer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
        progressBar.animate()
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.execute() }

            if (response.isSuccessful) {
                var data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    populateData(data)
                }
            }
        }
    }

    private fun populateData(data: Response) {
        recycleView.adapter = NewsAdaptor(
            data.articles!!,
            this
        )
        progressBar.clearAnimation()
        progressBar.visibility = View.GONE
    }

    override fun showData(url: String) {

        /*
        *  Intent Example to open url in system default browser
        *       startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
         */

        val customTabsIntent = CustomTabsIntent.Builder().build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}