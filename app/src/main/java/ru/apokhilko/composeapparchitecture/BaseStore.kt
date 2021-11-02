package ru.apokhilko.composeapparchitecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseStore<State, Effect, Action>: ViewModel() {
    protected val _state: MutableStateFlow<State> = createInitialState()
    val state: StateFlow<State> = _state.asStateFlow()
    protected var _effects: MutableStateFlow<Effect?> = createEffectsContainer()
    val effects: SharedFlow<Effect?> = _effects.asSharedFlow()

    abstract fun createInitialState(): MutableStateFlow<State>
    abstract fun createEffectsContainer(): MutableStateFlow<Effect?>
    abstract fun reducer(state: State, action: Action): State

    fun postSideEffect(effect: Effect) {
        _effects.value = effect
    }
}