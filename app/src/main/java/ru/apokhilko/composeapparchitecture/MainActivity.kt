package ru.apokhilko.composeapparchitecture

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.apokhilko.composeapparchitecture.ui.composable.main.MainScreen
import ru.apokhilko.composeapparchitecture.ui.composable.splash.SplashScreen
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme
import ru.apokhilko.composeapparchitecture.ui.composable.main.MainViewModel
import ru.apokhilko.composeapparchitecture.ui.composable.splash.SplashViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadMainWeather()
        viewModel.loadDaysData()
        viewModel.loadHoursData()

        setContent {
            ComposeAppArchitectureTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(splashViewModel, navController)
                    }
                    composable("main") {
                        MainScreen(viewModel)
                    }
                }
            }
        }
    }
}