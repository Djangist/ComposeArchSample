package ru.apokhilko.composeapparchitecture.domain.interactor

import dagger.hilt.android.scopes.ViewModelScoped
import ru.apokhilko.composeapparchitecture.data.WeatherDataRepository
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getMainWeatherData(refresh: Boolean = false): WeatherData
    suspend fun getDaysWeatherData(refresh: Boolean = false): List<WeatherData>
    suspend fun getHoursWeatherData(refresh: Boolean = false): List<WeatherData>
}

@ViewModelScoped
class WeatherInteractorImpl @Inject constructor(private val repository: WeatherDataRepository): WeatherInteractor {
    override suspend fun getDaysWeatherData(refresh: Boolean): List<WeatherData> = repository.getDaysWeatherData(refresh)

    override suspend fun getHoursWeatherData(refresh: Boolean): List<WeatherData> = repository.getHoursWeatherData(refresh)

    override suspend fun getMainWeatherData(refresh: Boolean): WeatherData = repository.getMainWeatherData(refresh)
}