package com.rcs.sensores.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rcs.sensores.viewmodel.SensorViewModel

@Composable
fun GyroSensorScreen() {
    val viewModel: SensorViewModel = viewModel()
    val data by viewModel.gyroData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startListening()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopListening()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Giroscopio", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("X: ${"%.4f".format(data.x)}")
            Text("Y: ${"%.4f".format(data.y)}")
            Text("Z: ${"%.4f".format(data.z)}")
        }
    }
}