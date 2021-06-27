package com.example.newsfeedapp

import okhttp3.OkHttpClient
import okhttp3.Request


object Client {

    private  var client = OkHttpClient()

    private val URL = "https://newsapi.org/v2/everything?q=tesla&from=2021-05-27&sortBy=publishedAt&apiKey=1f4a12d2698e432ea9cf18126dcc7acd"

    private var request = Request.Builder()
        .url(URL)
        .build()

    val api = client.newCall(request)

}