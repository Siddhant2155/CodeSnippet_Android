package com.example.recycleviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modelAdaptor = ModelAdaptor(
            Model.generateRandomCourses(40)
        )
        // Linear Layout
//        recycleView.layoutManager = LinearLayoutManager(this)
//        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)

        // Grid Layout
        recycleView.layoutManager = GridLayoutManager(this,3)
//        recycleView.layoutManager = GridLayoutManager(this,3, RecyclerView.HORIZONTAL, false)

        recycleView.adapter = modelAdaptor

    }
}