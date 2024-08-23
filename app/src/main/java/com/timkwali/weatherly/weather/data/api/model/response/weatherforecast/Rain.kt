package com.timkwali.weatherly.weather.data.api.model.response.weatherforecast


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val h: Double?
)