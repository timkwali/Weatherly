package com.timkwali.weatherly.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timkwali.weatherly.R
import com.timkwali.weatherly.core.presentation.components.image.ImageFromUrl
import com.timkwali.weatherly.core.presentation.components.text.BodyText
import com.timkwali.weatherly.core.presentation.components.text.TextWithDrawable
import com.timkwali.weatherly.weather.domain.model.weatherforecast.WeatherForecastState

@Composable
fun WeatherForecastItem(
    weatherForecastState: WeatherForecastState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyText(text = weatherForecastState.date, modifier = Modifier.weight(1f), fontSize = 15.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextWithDrawable(
                text = "${weatherForecastState.humidity}%",
                drawableStart = R.drawable.ic_water_drop,
                fontSize = 15.sp,
                iconSize = 15.dp,
                modifier = Modifier.padding(start = 15.dp)
            )

            ImageFromUrl(
                url = weatherForecastState.weatherIcon,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 15.dp)
            )

            BodyText(
                text = weatherForecastState.minimumTemperature + stringResource(id = R.string.degrees),
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 15.dp)
            )

            BodyText(
                text = weatherForecastState.maximumTemperature + stringResource(id = R.string.degrees),
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Preview
@Composable
fun WeatherForecastItemPreview() {
    WeatherForecastItem(weatherForecastState = WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),)
}