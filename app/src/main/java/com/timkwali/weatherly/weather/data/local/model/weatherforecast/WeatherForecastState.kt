package com.timkwali.weatherly.weather.data.local.model.weatherforecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.weatherly.core.utils.Constants.WEATHER_FORECAST_TABLE

@Entity(tableName = WEATHER_FORECAST_TABLE)
class WeatherForecastState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val humidity: String,
    val minimumTemperature: String,
    val maximumTemperature: String,
    val weatherIcon: String,
)