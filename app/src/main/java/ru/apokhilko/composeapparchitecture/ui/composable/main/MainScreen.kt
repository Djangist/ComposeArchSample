package ru.apokhilko.composeapparchitecture.ui.composable.main

import android.graphics.Color
import androidx.compose.animation.*
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.apokhilko.composeapparchitecture.R
import ru.apokhilko.composeapparchitecture.domain.WeatherData
import ru.apokhilko.composeapparchitecture.ui.theme.ComposeAppArchitectureTheme
import ru.apokhilko.composeapparchitecture.ui.theme.Purple700
import ru.apokhilko.composeapparchitecture.ui.theme.Typography

@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val mainWeather = viewModel.mainWeatherData.collectAsState()
    val daysWeather = viewModel.daysWeatherData.collectAsState()
    val hoursWeather = viewModel.hoursWeatherData.collectAsState()

    ShowWeather(
        viewModel,
        mainWeather.value,
        daysWeather.value,
        hoursWeather.value
    )
}

@ExperimentalAnimationApi
@Composable
fun ShowWeather(
    viewModel: MainViewModel,
    weatherData: WeatherData,
    dayItems: List<WeatherData>,
    hourItems: List<WeatherData>
) {
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Scaffold(
        topBar = { Toolbar() },
        backgroundColor = Purple700
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refreshData() }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ShowCity(weatherData.city)
                ShowWeatherImage(weatherData, isRefreshing)
                ShowDescription(weatherData.description)
                ShowHourlyWeatherItems(hourItems)
                ShowDaysWeatherItems(dayItems)
            }
        }
    }
}

@Composable
fun Toolbar() {
    TopAppBar(
        backgroundColor = Purple700,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                style = Typography.h6,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            IconButton(onClick = {

            }
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_bar), contentDescription = null)
            }
        }
    )
}

@Composable
fun ShowCity(city: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = city, style = Typography.h5)
    }
}

@ExperimentalAnimationApi
@Composable
fun ShowWeatherImage(weatherData: WeatherData, isRefreshing: Boolean) {
    Row(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(id = weatherData.weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ShowTemperature(weatherData.temperature, isRefreshing)
            ShowFeelingLike(weatherData.feelsLike)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Spacer(modifier = Modifier.size(16.dp))

            ShowWeatherSpeed(weatherData.weatherSpeed)
            ShowHumidity(weatherData.humidity)
            ShowCloudity(weatherData.cloudness)
            ShowPressure(weatherData.pressure)
            ShowVisibility(weatherData.visibilityMeters)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ShowTemperature(temprature: Int, isRefreshing: Boolean) {
    AnimatedVisibility(
        visible = !isRefreshing,
        enter = fadeIn(animationSpec = tween(1000))
    ) {
        Text(
            text = temprature.toString(),
            style = Typography.h3
        )
    }
}

@Composable
fun ShowFeelingLike(feelLike: String) {
    Text(
        text = feelLike,
        style = Typography.button,
        maxLines = 1,
        modifier = Modifier.widthIn(max = 100.dp),
    )
}

@Composable
fun ShowWeatherSpeed(weatherSpeed: Int) {
    Text(
        text = weatherSpeed.toString(),
        style = Typography.caption
    )
}

@Composable
fun ShowPressure(pressure: Int) {
    Text(
        text = pressure.toString(),
        style = Typography.caption
    )
}

@Composable
fun ShowHumidity(humidity: Int) {
    Text(
        text = humidity.toString(),
        style = Typography.caption
    )
}

@Composable
fun ShowCloudity(cloudity: Int) {
    Text(
        text = cloudity.toString(),
        style = Typography.caption
    )
}

@Composable
fun ShowVisibility(visibilityMeters: Int) {
    Text(
        text = visibilityMeters.toString(),
        style = Typography.caption
    )
}

@Composable
fun ShowDescription(description: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = description,
            style = Typography.h5
        )
    }
}

@Composable
fun ShowHourlyWeatherItems(hourItems: List<WeatherData>) {
    Spacer(modifier = Modifier.size(8.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        items(hourItems) {
            HoursItem(it)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun HoursItem(weatherData: WeatherData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = weatherData.time)
        Image(
            painter = painterResource(id = weatherData.weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
        Text(text = weatherData.temperature.toString())
    }
}

@Composable
fun ShowDaysWeatherItems(dayItems: List<WeatherData>) {
    Spacer(modifier = Modifier.size(16.dp))
    LazyColumn {
        items(dayItems) {
            DayItem(it)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun DayItem(weatherData: WeatherData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (column, image, text1, text2) = createRefs()
            Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier.constrainAs(column) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }) {
                Text(text = weatherData.dayOfMonth)
                Text(text = weatherData.dayOfWeek)
            }
            Image(
                painter = painterResource(id = weatherData.weatherIcon),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .constrainAs(image) {
                        end.linkTo(text1.start, margin = 8.dp)
                    }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = weatherData.maxTemp.toString(),
                style = Typography.body1,
                modifier = Modifier.constrainAs(text1) {
                    end.linkTo(text2.start, margin = 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Text(
                text = weatherData.minTemp.toString(),
                style = Typography.caption,
                modifier = Modifier.constrainAs(text2) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppArchitectureTheme {
        MainScreen()
    }
}