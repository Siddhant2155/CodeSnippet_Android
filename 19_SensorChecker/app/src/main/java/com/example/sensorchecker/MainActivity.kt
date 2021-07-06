package com.example.sensorchecker

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.getSystemService
import com.example.sensorchecker.databinding.ActivityMainBinding
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity() , SensorEventListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorEventListener: SensorEventListener
    lateinit var proxmitySensor: Sensor
    lateinit var accelSensor: Sensor


    val colors = arrayOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.DKGRAY, Color.LTGRAY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService<SensorManager>()!!

        proxmitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        binding.showSensorListBtn.setOnClickListener {
            val intent = Intent(this, SensorListActivity::class.java)
            startActivity(intent)
        }

        sensorEventListener = object: SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event!!.values[1] != null) {
                    binding.xaxis.text = "X: ${event!!.values[0]}"
                    binding.yaxis.text = "Y: ${event!!.values[1]}"
                    binding.zaxis.text = "Z: ${event!!.values[2]}"

                    val bgColor = Color.rgb(
                        convert2Color(event!!.values[0]),
                        convert2Color(event!!.values[1]),
                        convert2Color(event!!.values[2])
                    )
                    binding.mainLayout.setBackgroundColor( bgColor )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            proxmitySensor,
            1000 * 1000
        )
        sensorManager.registerListener(
            sensorEventListener,
            accelSensor,
            1000 * 1000
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this )
        sensorManager.unregisterListener(sensorEventListener)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 0) {
            binding.mainLayout.setBackgroundColor(colors[Random.nextInt(colors.size)])
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int)  { }

    fun convert2Color(value: Float) : Int = (((value + 12) / 24)  * 255).roundToInt()
}