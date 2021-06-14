package com.example.memeshare

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var imgURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchImage()
    }

    private fun fetchImage() {
        progressbar.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this)

        val url = "https://meme-api.herokuapp.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {

                    this@MainActivity.imgURL = response?.get("url") as String
                    Log.d("LGOGO", response.toString())
                    Log.d("LGOGO", imgURL)
                    val into = Glide.with(this@MainActivity)
                        .load(imgURL)
                        .listener(
                            object : RequestListener<Drawable> {
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    progressbar.visibility = View.GONE
                                    Toast.makeText(this@MainActivity,
                                            "Error Occur in fetching MEME",
                                            Toast.LENGTH_SHORT)
                                            .show()
                                    return false
                                }

                                override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    progressbar.visibility = View.GONE
                                    // if return true then => .into(imageView) wont work
                                    return false
                                }
                            }
                        )
                        .into(imageView)
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("LGOGO", error.toString())

                }
            })
        queue.add(jsonObjectRequest)
    }

    fun shareClick(view: View) {
        val intent = Intent().apply {
            setType("text/plain")
            setAction(Intent.ACTION_SEND)
            putExtra(Intent.EXTRA_TEXT, "Hey Checkout this Meme... $imgURL")
        }

// Always use string resources for UI text.
// This says something like "Share this photo with"
        val title = resources.getString(R.string.choose_title)
// Create intent to show chooser
        val chooser = Intent.createChooser(intent, title)
// Try to invoke the intent.
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Log.d("LGOGO", "shareClick: $e")
            // Define what your app should do if no activity can handle the intent.
        }
    }

    fun nextClick(view: View) {
        fetchImage()
    }
}