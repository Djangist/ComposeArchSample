package ru.apokhilko.composeapparchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ru.apokhilko.composeapparchitecture.ui.composable.main.ShowWeather
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppArchitectureTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    ShowWeather()
                }
            }
        }
    }
}