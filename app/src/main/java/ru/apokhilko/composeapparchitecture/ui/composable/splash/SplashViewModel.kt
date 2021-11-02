package ru.apokhilko.composeapparchitecture.ui.composable.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.BaseStore
import ru.apokhilko.composeapparchitecture.EmptyStore
import ru.apokhilko.composeapparchitecture.ui.Destinations
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    EmptyStore<SplashContract.State, SplashContract.Effect, SplashContract.Action>(SplashContract.State) {

    init {
        navigateToMain()
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            delay(3000)
            postSideEffect(SplashContract.Effect.NavigateTo(Destinations.Main))
        }
    }
}

interface SplashContract {
    object State

    sealed class Effect {
        class NavigateTo(val destinations: Destinations) : Effect()
    }

    object Action
}