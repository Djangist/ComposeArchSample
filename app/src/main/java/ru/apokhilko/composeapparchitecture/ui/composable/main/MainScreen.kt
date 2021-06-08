package ru.apokhilko.composeapparchitecture.ui.composable.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(text = "Твоя погода",
                        modifier = Modifier.fillMaxWidth(),
                        style = Typography.h6,
                        textAlign = TextAlign.Center)
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        },
        backgroundColor = Purple700
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ShowCity()
            ShowWeatherImage()
            ShowDescription()
            ShowHourlyWeatherItems()
            ShowDaysWeatherItems()
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
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_511_night),
            contentDescription = "weather_icon",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            contentScale = ContentScale.FillBounds
        )
        Column {
            ShowGradus()
            ShowFeelingLike()
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Spacer(modifier = Modifier.size(16.dp))
            ShowWeatherSpeed()
            ShowHumidity()
            ShowCloudity()
            ShowPressure()
            ShowVisibility()
        }
    }
}

@Composable
fun ShowGradus() {
    Text("19", style = Typography.h3)
}

@Composable
fun ShowFeelingLike() {
    Text("Feels like", style = Typography.button)
}

@Composable
fun ShowWeatherSpeed() {
    Text(text = "4 ms", style = Typography.caption)
}

@Composable
fun ShowPressure() {
    Text(text = "760 mm", style = Typography.caption)
}

@Composable
fun ShowHumidity() {
    Text(text = "76%", style = Typography.caption)
}

@Composable
fun ShowCloudity() {
    Text(text = "76%", style = Typography.caption)
}

@Composable
fun ShowVisibility() {
    Text(text = "10000 m", style = Typography.caption)
}

@Composable
fun ShowDescription() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("sunny", style = Typography.h5)
    }
}

@Composable
fun ShowHourlyWeatherItems() {
    Spacer(modifier = Modifier.size(8.dp))
    LazyRow() {
        items(20) {
            HoursItem()
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun HoursItem() {
    Column() {
        Text("20:00")
        Image(
            painter = painterResource(id = R.drawable.ic_511_night),
            contentDescription = null,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
        Text("18")
    }
}

@Composable
fun ShowDaysWeatherItems() {
    Spacer(modifier = Modifier.size(16.dp))
    LazyColumn() {
        items(7) {
            DayItem()
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun DayItem() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (column, image, text1, text2) = createRefs()
            Column(horizontalAlignment = Alignment.Start,
            modifier = Modifier.constrainAs(column) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }) {
                Text(text = "8 June")
                Text("Wednesday")
            }
            Image(
                painter = painterResource(id = R.drawable.ic_511_night),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .constrainAs(image) {
                        end.linkTo(text1.start, margin = 8.dp)
                    }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "20",
                style = Typography.body1,
                modifier = Modifier.constrainAs(text1){
                    end.linkTo(text2.start, margin = 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
            Text(text = "18",
                style = Typography.caption,
                modifier = Modifier.constrainAs(text2){
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppArchitectureTheme {
        ShowWeather()
    }
}