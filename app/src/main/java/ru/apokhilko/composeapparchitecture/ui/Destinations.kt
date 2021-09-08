package ru.apokhilko.composeapparchitecture.ui

sealed class Destinations {
    class Main: Destinations()
    object Details
    object Splash
}
