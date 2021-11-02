package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.EmptyStore
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractor
import javax.inject.Inject

typealias State = MainContract.State
typealias Effect = MainContract.Effect
typealias Action = MainContract.Action

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : EmptyStore<State, Effect, Action>(MainContract.State()) {

    init {
        loadData(fromRefresh = false)
    }

    override fun reducer(
        state: State,
        action: Action
    ): State {
        return when (action) {
            is MainContract.Action.SwipeToRefreshAction -> {
                state.copy(
                    isRefreshing = action.state.isRefreshing,
                    hoursWeather = action.state.hoursWeather,
                    daysWeather = action.state.daysWeather,
                    currentWeather = action.state.currentWeather
                )
            }
            is MainContract.Action.Loading -> {
                state.copy(isRefreshing = true)
            }
        }
    }

    private fun dispatchAction(action: Action) {
        _state.value = reducer(state = _state.value, action = action)
    }

    fun loadData(fromRefresh: Boolean) {
        viewModelScope.launch {
            if (fromRefresh) {
                delay(3000) // TODO temp
            }
            dispatchAction(
                action = MainContract.Action.SwipeToRefreshAction(
                    state = MainContract.State(
                        isRefreshing = false,
                        currentWeather = interactor.getMainWeatherData(fromRefresh),
                        hoursWeather = interactor.getHoursWeatherData(fromRefresh),
                        daysWeather = interactor.getDaysWeatherData(fromRefresh)
                    )
                )
            )
        }
        dispatchAction(action = MainContract.Action.Loading)
    }
}

interface MainContract {
    data class State(
        val isRefreshing: Boolean = false,
        val currentWeather: WeatherData = WeatherData(),
        val hoursWeather: List<WeatherData> = emptyList(),
        val daysWeather: List<WeatherData> = emptyList()
    )

    sealed class Action {
        class SwipeToRefreshAction(val state: State) : Action()
        object Loading : Action()
    }

    object Effect
}