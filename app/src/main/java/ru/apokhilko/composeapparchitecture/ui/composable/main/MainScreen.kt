package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
            showDescription()
            showHourlyWeatherItems()
            showDaysWeatherItems()
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
            Column {
                Spacer(modifier = Modifier.size(16.dp))
                showWeatherSpeed()
                showHumidity()
                showCloudity()
                showPressure()
                showVisibility()
            }
        }
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
fun showPressure() {
    Text(text = "760 mm", style = Typography.caption)
}

@Composable
fun showHumidity() {
    Text(text = "76%", style = Typography.caption)
}

@Composable
fun showCloudity() {
    Text(text = "76%", style = Typography.caption)
}

@Composable
fun showVisibility() {
    Text(text = "10000 m", style = Typography.caption)
}

@Composable
fun showDescription() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
        Text("sunny", style = Typography.h5)
    }
}

@Composable
fun showHourlyWeatherItems() {
    LazyRow() {
        
    }
}

@Composable
fun showDaysWeatherItems() {
    LazyColumn() {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppArchitectureTheme {
        ShowWeather()
    }
}