package com.rcs.sensores.ui.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rcs.sensores.nav.NavigationRoutes

@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2)) // Azul más oscuro
    ) {
        // Título centrado
        Text(
            text = "Conociendo tu celular",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            textAlign = TextAlign.Center
        )

        // Contenedor principal para los botones
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 80.dp)
        ) {
            // Botón 1 - Centrado a la izquierda (no en la parte superior)
            Button(
                onClick = { navController.navigate(NavigationRoutes.SCREEN_1) },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(y = (-50).dp) // Subir un poco desde el centro
                    .size(width = 150.dp, height = 60.dp)
            ) {
                Text("Pantalla")
            }

            // Botón 2 - Centrado a la derecha y abajo (escalonado)
            Button(
                onClick = { navController.navigate(NavigationRoutes.SCREEN_2) },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(y = 40.dp) // Bajar un poco desde el centro
                    .size(width = 150.dp, height = 60.dp)
            ) {
                Text("Sensores")
            }

            // Botón 3 - Centrado abajo a la izquierda
            Button(
                onClick = { navController.navigate(NavigationRoutes.SCREEN_3) },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 156.dp)
                    .size(width = 150.dp, height = 60.dp)
            ) {
                Text("Comentarios")
            }
        }

        // Texto informativo inferior izquierdo
        Text(
            text = "Fuentes Guzman Abel",
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(56.dp)
        )

        // Botón de salir - Abajo derecha (verde)
        Button(
            onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                } else {
                    (context as? Activity)?.finish()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),  // Verde Material 500
                contentColor = Color.White           // Texto en blanco
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(56.dp)
                .offset(x = 45.dp) // Desplazamiento positivo hacia la derecha
                .size(width = 120.dp, height = 50.dp)
        ) {
            Text("Salir")
        }
    }
}