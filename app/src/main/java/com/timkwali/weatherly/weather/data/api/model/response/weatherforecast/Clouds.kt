package com.timkwali.weatherly.weather.data.api.model.response.weatherforecast


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)