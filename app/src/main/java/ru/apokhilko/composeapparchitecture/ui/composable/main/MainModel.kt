package ru.apokhilko.composeapparchitecture.ui.composable.main

import ru.apokhilko.composeapparchitecture.domain.WeatherData

data class MainModel(
    val isRefreshing: Boolean = false,
    val currentWeather: WeatherData = WeatherData(),
    val hoursWeather: List<WeatherData> = emptyList(),
    val daysWeather: List<WeatherData> = emptyList()
)
