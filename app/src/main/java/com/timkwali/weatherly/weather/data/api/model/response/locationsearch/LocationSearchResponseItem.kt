package com.timkwali.weatherly.weather.data.api.model.response.locationsearch


import com.google.gson.annotations.SerializedName

data class LocationSearchResponseItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("local_names")
    val localNames: LocalNames?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("state")
    val state: String?
)