package com.example.tabbarinandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tabbarinandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var fragment1: Fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.replaceView, fragment1)
//                    .add(binding.replaceView.tag, fragment1)
                .commit()

        }
        binding.imageButton2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.replaceView, Fragment2())
                .commit()
        }
        binding.imageButton3.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.replaceView,  Fragment3())
                .commit()
        }
        binding.imageButton4.setOnClickListener(this)

        fragment1 = Fragment1()
    }

    override fun onClick(v: View) {
        when (v.tag) {
            R.id.imageButton -> {
                 Log.d("test","test")
//                this@MainActivity.
            }
            R.id.imageButton2 -> {

            }
            binding.imageButton3.tag -> {
                Log.d("test","test")
            }
        }
    }
}