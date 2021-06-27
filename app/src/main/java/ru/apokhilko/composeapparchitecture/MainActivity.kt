package ru.apokhilko.composeapparchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import ru.apokhilko.composeapparchitecture.ui.composable.main.ShowWeather
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme
import ru.apokhilko.composeapparchitecture.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadMainWeather()
        viewModel.loadDaysData()
        viewModel.loadHoursData()

        setContent {
            ComposeAppArchitectureTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    val mainWeather = viewModel.mainWeatherData.value
                    val daysWeather = viewModel.daysWeatherData.value
                    val hoursWeather = viewModel.hoursWeatherData.value
                    ShowWeather(
                        viewModel.isRefreshing.value,
                        mainWeather,
                        daysWeather,
                        hoursWeather
                    ) { viewModel.refreshData() }
                }
            }
        }
    }
}