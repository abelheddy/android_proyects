package com.rcs.sensores.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.rcs.sensores.model.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SensorViewModel(app: Application) : AndroidViewModel(app), SensorEventListener {

    private val sensorManager = app.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val _gyroData = MutableStateFlow(SensorData())
    val gyroData = _gyroData.asStateFlow()

    private val firebaseRef = FirebaseDatabase.getInstance().getReference("gyroscopeData")

    fun startListening() {
        gyroSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val data = SensorData(it.values[0], it.values[1], it.values[2])
            _gyroData.value = data

            // Subir a Firebase
            viewModelScope.launch {
                firebaseRef.push().setValue(data)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}