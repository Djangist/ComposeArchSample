package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container = container<MainState, MainSideEffect>(MainState()) {
        loadWeather()
    }

    private fun loadWeather(fromRefresh: Boolean = false) = intent {
        val mainWeather = interactor.getMainWeatherData(fromRefresh)
        val daysWeather = interactor.getDaysWeatherData(fromRefresh)
        val hoursWeather = interactor.getHoursWeatherData(fromRefresh)
        reduce {
            state.copy(
                mainWeather = mainWeather,
                daysWeather = daysWeather,
                hoursWeather = hoursWeather,
                isRefreshing = false
            )
        }
    }

    fun pullToRefresh() = intent {
        reduce {
            state.copy(isRefreshing = true)
        }
        delay(3000) //TODO just for sample
        loadWeather(fromRefresh = true)
    }
}

data class MainState(
    var mainWeather: WeatherData = WeatherData(),
    val daysWeather: List<WeatherData> = emptyList(),
    val hoursWeather: List<WeatherData> = emptyList(),
    val isRefreshing: Boolean = false,
)

sealed class MainSideEffect