package com.rcs.sensores.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rcs.sensores.viewmodel.ComentariosViewModel

@Composable
fun ComentariosScreen(navController: NavController) {
    // Instancia del ViewModel
    val viewModel: ComentariosViewModel = viewModel()

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var feedbackMessage by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2196F3) // fondo azul
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Comentarios", color = Color.White, modifier = Modifier.padding(bottom = 16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                // El texto es negro por defecto, si quieres blanco habría que añadir estilo pero mejor lo dejamos simple
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = mensaje,
                onValueChange = { mensaje = it },
                label = { Text("Comentario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombre.isBlank() || email.isBlank() || mensaje.isBlank()) {
                        feedbackMessage = "Por favor completa todos los campos"
                        return@Button
                    }
                    isSending = true
                    feedbackMessage = ""

                    // Aquí manda al ViewModel que haga el envío a Firebase
                    viewModel.enviarComentario(nombre, email, mensaje) { success ->
                        isSending = false
                        feedbackMessage = if (success) {
                            "Comentario enviado con éxito"
                        } else {
                            "Error al enviar comentario"
                        }
                        if(success) {
                            nombre = ""
                            email = ""
                            mensaje = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSending
            ) {
                Text(text = if (isSending) "Enviando..." else "Enviar")
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (feedbackMessage.isNotEmpty()) {
                Text(text = feedbackMessage, color = Color.Yellow)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 92.dp)
                    .width(200.dp),
            ) {
                Text("Regresar")
            }
        }
    }
}
