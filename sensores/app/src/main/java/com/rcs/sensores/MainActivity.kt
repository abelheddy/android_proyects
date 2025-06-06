package com.rcs.sensores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.rcs.sensores.nav.NavigationRoutes
import com.rcs.sensores.ui.screen.ComentariosScreen
import com.rcs.sensores.ui.screen.GyroSensorScreen
import com.rcs.sensores.ui.screen.MenuScreen
import com.rcs.sensores.ui.screen.TouchScreen
import com.rcs.sensores.ui.theme.SensoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            SensoresTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.MENU_SCREEN
                    ) {
                        composable(NavigationRoutes.MENU_SCREEN) {
                            MenuScreen(navController = navController)
                        }
                        composable(NavigationRoutes.SCREEN_1) {
                            TouchScreen(navController = navController)
                        }
                        composable(NavigationRoutes.SCREEN_2) {
                            GyroSensorScreen(navController = navController)
                        }
                        composable(NavigationRoutes.SCREEN_3) {
                            ComentariosScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


