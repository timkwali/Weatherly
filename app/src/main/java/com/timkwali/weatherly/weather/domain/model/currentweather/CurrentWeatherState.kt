package com.timkwali.weatherly.weather.domain.model.currentweather

data class CurrentWeatherState (
    val locationName: String,
    val feelsLike: String,
    val temperature: String,
    val humidity: String,
    val windSpeed: String,
    val weatherDescription: String,
    val weatherIcon: String
)