package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.domain.interactor.WeatherInteractor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : ViewModel() {

    // --- main state
    private var _state: MutableStateFlow<MainContract.MainState> =
        MutableStateFlow(MainContract.MainState.InitialState)
    val state: StateFlow<MainContract.MainState> = _state.asStateFlow()

    init {
        loadData(fromRefresh = false)
    }

    fun dispatchAction(action: MainContract.MainAction) {
        reducer(state = _state.value, action = action)
    }

    private fun reducer(
        state: MainContract.MainState = MainContract.MainState.InitialState,
        action: MainContract.MainAction
    ): MainContract.MainState {
        return when (action) {
            is MainContract.MainAction.SwipeToRefreshAction -> {
                _state.value = loadData(fromRefresh = true)
                _state.value
            }
            else -> state
        }
    }

    private fun loadData(fromRefresh: Boolean): MainContract.MainState {
        viewModelScope.launch {
            if( fromRefresh ) {
                delay(3000) // TODO temp
            }
            updateData(fromRefresh = fromRefresh)
        }
        return MainContract.MainState.LoadingState(
            data = MainModel(isRefreshing = true)
        )
    }

    private fun updateData(fromRefresh: Boolean) {
        viewModelScope.launch {
            _state.value = MainContract.MainState.UpdateDataState(
                data = MainModel(
                    isRefreshing = false,
                    currentWeather = interactor.getMainWeatherData(fromRefresh),
                    hoursWeather = interactor.getHoursWeatherData(fromRefresh),
                    daysWeather = interactor.getDaysWeatherData(fromRefresh)
                )
            )
        }
    }
}