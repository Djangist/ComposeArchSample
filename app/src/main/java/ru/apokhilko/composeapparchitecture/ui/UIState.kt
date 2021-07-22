package ru.apokhilko.composeapparchitecture.ui

sealed class UIState {
    object Loading : UIState()
    object Showing: UIState()
    class NavigateTo(val destination: String) : UIState()
}