package com.timkwali.weatherly.weather.data.api.model.response.weatherforecast


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String?
)