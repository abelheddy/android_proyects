package com.rcs.sensores.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rcs.sensores.viewmodel.SensorViewModel

@Composable
fun GyroSensorScreen(
    navController: NavController
) {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2)) // Fondo azul
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sensores",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(124.dp))

            Text(
                text = "Giroscopio",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(54.dp))

            Text("X: ${"%.4f".format(data.x)}", color = Color.White, fontSize = 18.sp)
            Text("Y: ${"%.4f".format(data.y)}", color = Color.White, fontSize = 18.sp)
            Text("Z: ${"%.4f".format(data.z)}", color = Color.White, fontSize = 18.sp)
        }

        // Botón de regreso, alineado al fondo
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 92.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("Regresar al Menú", color = Color(0xFF2196F3))
        }
    }
}
