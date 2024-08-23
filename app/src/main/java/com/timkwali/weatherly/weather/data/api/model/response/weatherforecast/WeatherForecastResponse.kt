package com.timkwali.weatherly.weather.data.api.model.response.weatherforecast


import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<AverageWeather?>?,
    @SerializedName("message")
    val message: Int?
)