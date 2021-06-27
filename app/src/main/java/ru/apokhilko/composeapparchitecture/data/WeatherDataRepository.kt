package ru.apokhilko.composeapparchitecture.data

import ru.apokhilko.composeapparchitecture.domain.WeatherData

interface WeatherDataRepository {
    suspend fun getMainWeatherData(refresh: Boolean = false): WeatherData
    suspend fun getDaysWeatherData(refresh: Boolean = false): List<WeatherData>
    suspend fun getHoursWeatherData(refresh: Boolean = false): List<WeatherData>
}