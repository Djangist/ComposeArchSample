package ru.apokhilko.composeapparchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.apokhilko.composeapparchitecture.ui.composable.main.MainScreen
import ru.apokhilko.composeapparchitecture.ui.composable.splash.SplashScreen
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppArchitectureTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(navController)
                    }
                    composable("main") {
                        MainScreen()
                    }
                }
            }
        }
    }
}