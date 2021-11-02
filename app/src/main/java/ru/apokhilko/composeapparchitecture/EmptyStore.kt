package ru.apokhilko.composeapparchitecture

import kotlinx.coroutines.flow.MutableStateFlow

open class EmptyStore<State, Effect, Action>(private val initialState: State) :
    BaseStore<State, Effect, Action>() {

    override fun createInitialState(): MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun createEffectsContainer(): MutableStateFlow<Effect?> = MutableStateFlow(null)

    /**
     * empty reducer
     */
    override fun reducer(state: State, action: Action): State = _state.value
}