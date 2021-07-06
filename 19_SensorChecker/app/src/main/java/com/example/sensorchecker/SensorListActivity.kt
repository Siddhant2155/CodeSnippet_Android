package com.example.sensorchecker

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorchecker.databinding.ActivitySensorListBinding

class SensorListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySensorListBinding
    private var sensorListData = arrayListOf<SensorList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensorListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sensorManager = getSystemService<SensorManager>()

        val sensorList = sensorManager?.getSensorList(Sensor.TYPE_ALL)

        sensorList?.let { st ->
            st.forEach {
                sensorListData.add(SensorList(
                    it.name,
                    it.stringType,
                    it.version,
                    it.vendor,
                    it.maximumRange,
                    it.power
                ))
            }
        }

        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = SensorListAdaptor(sensorListData)
    }
}