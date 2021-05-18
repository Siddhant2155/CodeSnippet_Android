package com.example.todoapp_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: ArrayList<String> = ArrayList()
        val adaptor = MainAdapter(
            list
        )
        recylView.layoutManager = LinearLayoutManager(this)


        addBtn.setOnClickListener{
            if(!txtId.text.isEmpty()) {
                list.add( txtId.text.toString())
                txtId.text.clear()
                adaptor.notifyItemInserted( list.size -1)
            }
        }
        recylView.adapter = adaptor
    }
}