package com.rcs.sensores.model

data class SensorData(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)
