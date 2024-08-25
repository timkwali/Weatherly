package com.timkwali.weatherly.weather.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timkwali.weatherly.R
import com.timkwali.weatherly.core.presentation.components.image.IconFromDrawable
import com.timkwali.weatherly.core.presentation.components.text.BodyText
import com.timkwali.weatherly.core.presentation.theme.WeatherlyBlue
import com.timkwali.weatherly.core.presentation.theme.WeatherlyDeepBlue
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationState

@Composable
fun LocationItem(
    location: LocationState,
    onLocationClick: (location: LocationState) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyText(
            text = location.displayName,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1f)
                .clickable { onLocationClick(location) }
        )
        Spacer(modifier = Modifier.width(5.dp))
        IconFromDrawable(drawable = R.drawable.ic_arrow_forward, size = 20.dp, tint = MaterialTheme.colorScheme.onSecondary)
    }
}

@Preview
@Composable
fun LocationItemPreview() {
    LocationItem(
        location = LocationState(
            "London- Ontario(CA)",
            "42.9832406",
            "-81.243372",
        ),
        onLocationClick = {}
    )
}