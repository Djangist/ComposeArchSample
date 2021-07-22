package ru.apokhilko.composeapparchitecture.ui.composable.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ru.apokhilko.composeapparchitecture.R
import ru.apokhilko.composeapparchitecture.ui.UIState
import ru.apokhilko.composeapparchitecture.ui.theme.SplashColor

const val TAG = "Compose"

@Composable
fun SplashScreen(viewModel: SplashViewModel, navController: NavController)  {
    val state = remember { viewModel.state }
    if( state.value is UIState.NavigateTo ){
        Log.d(TAG,"navigate to main")
        navController.popBackStack()
        navController.navigate("main")
    }

    Log.d(TAG,"splash recompose ${state.value}")
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