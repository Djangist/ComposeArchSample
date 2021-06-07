package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.apokhilko.composeapparchitecture.R
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme
import ru.apokhilko.composeapparchitecture.ui.theme.Purple700
import ru.apokhilko.composeapparchitecture.ui.theme.Typography

@Composable
fun ShowWeather() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "My Weather") })
        },
        backgroundColor = Purple700
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ShowCity()
            ShowWeatherImage()
        }
    }
}

@Composable
fun ShowCity() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Ростов-на-Дону", style = Typography.h5)
    }
}

@Composable
fun ShowWeatherImage() {
    ConstraintLayout() {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.ic_511_night),
                contentDescription = "weather_icon",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                contentScale = ContentScale.FillBounds
            )
            Column {
                showGradus()
                showFeelingLike()
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column() {
                showWeatherSpeed()
                showHumidity()
                showCloudity()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppArchitectureTheme {
        ShowCity()
        ShowWeatherImage()
    }
}

@Composable
fun showGradus() {
    Text("19", style = Typography.h3)
}

@Composable
fun showFeelingLike() {
    Text("Feels like", style = Typography.button)
}

@Composable
fun showWeatherSpeed() {
    Text(text = "4 ms", style = Typography.caption)
}

@Composable
fun showHumidity() {
    Text(text = "760 mm", style = Typography.caption)
}

@Composable
fun showCloudity() {
    Text(text = "76%", style = Typography.caption)
}