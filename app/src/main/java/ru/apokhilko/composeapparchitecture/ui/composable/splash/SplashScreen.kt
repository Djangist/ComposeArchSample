package ru.apokhilko.composeapparchitecture.ui.composable.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.apokhilko.composeapparchitecture.R
import ru.apokhilko.composeapparchitecture.ui.theme.SplashColor

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = hiltViewModel()
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect {
                if (it is SplashSideEffect.NavigateTo) {
                    navController.popBackStack()
                    navController.navigate(it.destination)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(SplashColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = null,
            alignment = Alignment.Center
        )
    }
}