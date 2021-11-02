package ru.apokhilko.composeapparchitecture.ui

sealed class Destinations(val screen: String) {
    object Main: Destinations("main")
    object Details
    object Splash
}
