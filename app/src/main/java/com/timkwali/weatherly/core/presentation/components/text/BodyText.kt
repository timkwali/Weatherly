package com.timkwali.weatherly.core.presentation.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight
        ),
        modifier = modifier
    )
}