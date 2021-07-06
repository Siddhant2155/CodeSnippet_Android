package com.example.sensorchecker

data class SensorList(
    val name: String,
    val type: String,
    val version: Int,
    val vendor: String,
    val maxRange: Float,
    val power: Float
)
