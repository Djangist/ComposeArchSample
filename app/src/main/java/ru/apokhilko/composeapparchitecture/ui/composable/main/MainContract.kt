package ru.apokhilko.composeapparchitecture.ui.composable.main

import ru.apokhilko.composeapparchitecture.ui.Action
import ru.apokhilko.composeapparchitecture.ui.State

interface MainContract {

    sealed class MainState(val data: MainModel = MainModel()): State {
        object InitialState: MainState()
        class LoadingState(data: MainModel) : MainState(data = data)
        class UpdateDataState(data: MainModel): MainState(data = data)
    }

    sealed class MainAction: Action {
        object SwipeToRefreshAction: MainAction()
    }
}