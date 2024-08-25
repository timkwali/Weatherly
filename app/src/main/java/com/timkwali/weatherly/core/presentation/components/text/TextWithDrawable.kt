package com.timkwali.weatherly.core.presentation.components.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timkwali.weatherly.core.presentation.components.image.IconFromDrawable

@Composable
fun TextWithDrawable(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = MaterialTheme.colorScheme.secondary,
    iconTint: Color = MaterialTheme.colorScheme.secondary,
    iconSize: Dp = 40.dp,
    drawableStart: Int? = null,
    drawableEnd: Int? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(drawableStart != null) {
            IconFromDrawable(
                drawable = drawableStart,
                tint = iconTint,
                size = iconSize
            )
            Spacer(modifier = Modifier.width(2.dp))
        }

        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                color = color,
                fontWeight = fontWeight
            )
        )

        if(drawableEnd != null) {
            Spacer(modifier = Modifier.width(5.dp))
            IconFromDrawable(
                drawable = drawableEnd,
                tint = iconTint,
                size = iconSize
            )
        }
    }
}