package com.rcs.sensores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import com.rcs.sensores.ui.screen.GyroSensorScreen
import com.rcs.sensores.ui.theme.SensoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            SensoresTheme {
                GyroSensorScreen()
            }
        }
    }
}

