package ru.apokhilko.composeapparchitecture.ui.composable.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(), ContainerHost<SplashState, SplashSideEffect> {

    override val container = container<SplashState, SplashSideEffect>(SplashState.Showing)

    init {
        viewModelScope.launch {
            navigateToMain()
        }
    }

    private fun navigateToMain() = intent {
        delay(3000)
        postSideEffect(SplashSideEffect.NavigateTo("main"))
    }
}

sealed class SplashState {
    object Showing: SplashState()
}

sealed class SplashSideEffect {
    class NavigateTo(val destination: String) : SplashSideEffect()
}