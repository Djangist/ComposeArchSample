package ru.apokhilko.composeapparchitecture.ui.composable.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.ui.Destinations
import ru.apokhilko.composeapparchitecture.ui.UIState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf<UIState>(UIState.Showing)
    val state
        get() = _state

    init {
        viewModelScope.launch {
            delay(3000)
            _state.value = UIState.NavigateTo(Destinations.Main())
        }
    }
}