package com.timkwali.weatherly.weather.data.api.model.response.currentweather


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double?
)