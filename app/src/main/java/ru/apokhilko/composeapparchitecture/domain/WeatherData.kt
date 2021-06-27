package ru.apokhilko.composeapparchitecture.domain

import androidx.annotation.DrawableRes
import ru.apokhilko.composeapparchitecture.R

data class WeatherData(
    val description: String = "облачно",
    val city: String = "Ростов-на-Дону",
    @DrawableRes val weatherIcon: Int = R.drawable.ic_511_night,
    val temperature: Int = 25,
    val feelsLike: String = "+20",
    val weatherSpeed: Int = 20,
    val pressure: Int = 760,
    val humidity: Int = 70,
    val visibilityMeters: Int = 1000,
    val cloudness: Int = 80,
    val time: String = "20:00",
    val dayOfMonth: String = "17 Июня",
    val dayOfWeek: String = "Четверг",
    val minTemp: Int = 18,
    val maxTemp: Int = 22
)