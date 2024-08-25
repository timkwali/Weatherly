package com.timkwali.weatherly.weather.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.timkwali.weatherly.R
import com.timkwali.weatherly.core.presentation.animations.circularMotion
import com.timkwali.weatherly.core.presentation.components.image.IconFromDrawable
import com.timkwali.weatherly.core.presentation.components.image.ImageFromUrl
import com.timkwali.weatherly.core.presentation.components.text.BodyText
import com.timkwali.weatherly.core.presentation.components.text.TextWithDrawable
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState

@Composable
fun CurrentWeatherSection(
    currentWeather: CurrentWeatherState,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    ) {
        val (searchIcon, locationName, temperature, degrees, weatherDescription, weatherIcon, details) = createRefs()
        val horizontalMiddlePoint = createGuidelineFromEnd(0.5f)

        IconFromDrawable(
            drawable = R.drawable.ic_search,
            modifier = Modifier
                .size(40.dp)
                .constrainAs(searchIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            onClick = { onSearchClick() }
        )

        TextWithDrawable(
            text = currentWeather.locationName,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            iconSize = 25.dp,
            drawableStart = R.drawable.ic_location_on,
            modifier = Modifier
                .constrainAs(locationName) {
                    start.linkTo(parent.start)
                    top.linkTo(searchIcon.top)
                    bottom.linkTo(searchIcon.bottom)
                }
        )

        BodyText(
            text = currentWeather.temperature + stringResource(id = R.string.degrees),
            fontSize = 100.sp,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(temperature) {
                    start.linkTo(parent.start)
                    top.linkTo(searchIcon.bottom)
                }
        )

        BodyText(
            text = currentWeather.weatherDescription.capitalize(Locale.current),
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(weatherDescription) {
                    start.linkTo(parent.start)
                    top.linkTo(temperature.bottom)
                }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .constrainAs(details) {
                    top.linkTo(weatherDescription.bottom)
                }
        ) {
            BodyText(text = "${stringResource(id = R.string.feels_like)} ${currentWeather.feelsLike}" + stringResource(id = R.string.degrees))
            TextWithDrawable(
                text = "${currentWeather.humidity}%",
                drawableStart = R.drawable.ic_water_drop,
                iconSize = 20.dp,
                modifier = Modifier.padding(start = 15.dp)
            )
            TextWithDrawable(
                text = "${currentWeather.windSpeed}m/s",
                drawableStart = R.drawable.ic_wind,
                iconSize = 20.dp,
                modifier = Modifier.padding(start = 15.dp)
            )
        }


        ImageFromUrl(
            url = currentWeather.weatherIcon,
            modifier = Modifier
                .size(150.dp)
                .circularMotion(20f, 8000)
                .constrainAs(weatherIcon) {
                    start.linkTo(horizontalMiddlePoint)
                    end.linkTo(parent.end)
                    top.linkTo(temperature.top)
                    bottom.linkTo(temperature.bottom)
                }
        )
    }
}

@Composable
@Preview
fun CurrentWeatherSectionPreview() {
    CurrentWeatherSection(
        currentWeather = CurrentWeatherState(
            locationName = "Lagos",
            feelsLike = "356",
            temperature = "23",
            humidity = "88",
            windSpeed = "25",
            weatherDescription = "Rainy day",
            weatherIcon = "https://openweathermap.org/img/wn/10d@2x.png"
        ),
        onSearchClick = {}
    )
}