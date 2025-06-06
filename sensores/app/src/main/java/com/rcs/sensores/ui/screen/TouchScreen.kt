package com.rcs.sensores.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rcs.sensores.R // Asegúrate de tener tu recurso de imagen
@Composable
fun TouchScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2)) // Mismo azul que el menú
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Pantalla Táctil",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Subtítulo o descripción
            Text(
                text = "(Debes poner información relacionada con la pantalla táctil)",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                textAlign = TextAlign.Center
            )
        }

        // Contenedor del cuadro central con imagen
        Box(
            modifier = Modifier
                .size(300.dp) // Tamaño del cuadro
                .align(Alignment.Center)
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            // Imagen centrada
            Image(
                painter = painterResource(id = R.drawable.touch_screen),
                contentDescription = "Ejemplo de pantalla táctil",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Descripción inferior
        Text(
            text = "En esta parte debes mostrar una práctica utilizando la pantalla táctil",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 138.dp),
            textAlign = TextAlign.Center
        )

        // Botón de regreso
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 92.dp)
                .width(200.dp)
        ) {
            Text("Regresar al Menú")
        }
    }
}
