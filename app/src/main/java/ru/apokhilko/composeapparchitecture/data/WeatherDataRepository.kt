package ru.apokhilko.composeapparchitecture.data

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import javax.inject.Inject

interface WeatherDataRepository {
    suspend fun getMainWeatherData(refresh: Boolean = false): WeatherData
    suspend fun getDaysWeatherData(refresh: Boolean = false): List<WeatherData>
    suspend fun getHoursWeatherData(refresh: Boolean = false): List<WeatherData>
}

@ViewModelScoped
class WeatherDataRepositoryImpl @Inject constructor() : WeatherDataRepository {

    override suspend fun getDaysWeatherData(refresh: Boolean): List<WeatherData> =
        withContext(Dispatchers.Main) {
            if (!refresh) {
                listOf(WeatherData(), WeatherData(), WeatherData())
            } else {
                listOf(WeatherData(), WeatherData(), WeatherData(), WeatherData(), WeatherData())
            }
        }

    override suspend fun getHoursWeatherData(refresh: Boolean): List<WeatherData> =
        withContext(Dispatchers.Main) {
            if (refresh) {
                val hours = mutableListOf<WeatherData>()
                (1..24).forEach {
                    hours.add(WeatherData(temperature = (Math.random() * 10).toInt()))
                }
                hours
            } else {
                listOf(WeatherData(), WeatherData(), WeatherData())
            }
        }

    override suspend fun getMainWeatherData(refresh: Boolean): WeatherData =
        withContext(Dispatchers.Main) {
            if (refresh) {
                WeatherData(temperature = (Math.random() * 10).toInt())
            } else {
                WeatherData()
            }
        }
}