package com.example.sensorchecker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sensorchecker.databinding.SensorListItemBinding

class SensorListAdaptor(val sensorList: List<SensorList>): RecyclerView.Adapter<SensorListAdaptor.SensorListViewHolder>() {


    class SensorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var binding: SensorListItemBinding

        init {
            binding = SensorListItemBinding.bind(itemView.rootView)
        }
        fun bind(sensor: SensorList) {
            binding.nameLbl.text = sensor.name
            "Vendor => ${sensor.vendor}".also { binding.vendor.text = it }
            binding.version.text = "Version => ${sensor.version}"
            binding.type.text = "Type => " + sensor.type
            binding.range.text = "Range: ${sensor.maxRange}"
            binding.power.text = "Power: ${sensor.power}"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorListViewHolder {
        return SensorListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.sensor_list_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: SensorListViewHolder, position: Int) {
        holder.bind(sensorList[position])
    }

    override fun getItemCount(): Int = sensorList.size
}