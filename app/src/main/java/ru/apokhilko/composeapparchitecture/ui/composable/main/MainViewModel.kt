package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : ViewModel() {

    private val _mainWeatherData = MutableStateFlow(WeatherData())
    val mainWeatherData: StateFlow<WeatherData> = _mainWeatherData.asStateFlow()

    private val _daysWeatherData = MutableStateFlow<List<WeatherData>>(emptyList())
    val daysWeatherData: StateFlow<List<WeatherData>> = _daysWeatherData.asStateFlow()

    private val _hoursWeatherData = MutableStateFlow<List<WeatherData>>(emptyList())
    val hoursWeatherData: StateFlow<List<WeatherData>> = _hoursWeatherData.asStateFlow()

    private var _isRefreshing = MutableStateFlow(false  )
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadMainWeather()
        loadDaysData()
        loadHoursData()
    }

    fun loadMainWeather(fromRefresh: Boolean = false) {
        viewModelScope.launch {
            val weatherData = interactor.getMainWeatherData(fromRefresh)
            _mainWeatherData.value = weatherData
        }
    }

    fun loadDaysData(fromRefresh: Boolean = false) {
        viewModelScope.launch {
            val weatherData = interactor.getDaysWeatherData(fromRefresh)
            _daysWeatherData.value = weatherData
        }
    }

    fun loadHoursData(fromRefresh: Boolean = false) {
        viewModelScope.launch {
            val weatherData = interactor.getHoursWeatherData(fromRefresh)
            _hoursWeatherData.value = weatherData
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(3000) // TODO temporary

            loadMainWeather(fromRefresh = true)
            loadDaysData(fromRefresh = true)
            loadHoursData(fromRefresh = true)

            _isRefreshing.emit( false)
        }
    }
}