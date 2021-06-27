package ru.apokhilko.composeapparchitecture.domain.interactor

import ru.apokhilko.composeapparchitecture.domain.WeatherData

interface WeatherInteractor {
    suspend fun getMainWeatherData(refresh: Boolean = false): WeatherData
    suspend fun getDaysWeatherData(refresh: Boolean = false): List<WeatherData>
    suspend fun getHoursWeatherData(refresh: Boolean = false): List<WeatherData>
}