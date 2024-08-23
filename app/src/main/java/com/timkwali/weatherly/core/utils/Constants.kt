package com.timkwali.weatherly.core.utils

import com.timkwali.weatherly.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val DEFAULT_FORECAST_DAY_COUNT = "7"
    const val DEFAULT_LOCATION_SEARCH_LIMIT = "5"
    const val BASE_URL = "https://api.openweathermap.org/"
    const val CURRENT_WEATHER_ENDPOINT = "data/2.5/weather"
    const val WEATHER_FORECAST_ENDPOINT = "data/2.5/forecast/daily"
    const val LOCATION_SEARCH_ENDPOINT = "geo/1.0/direct"

}