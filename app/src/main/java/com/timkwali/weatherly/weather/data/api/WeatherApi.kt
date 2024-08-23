package com.timkwali.weatherly.weather.data.api

import com.timkwali.weatherly.core.utils.Constants.API_KEY
import com.timkwali.weatherly.core.utils.Constants.CURRENT_WEATHER_ENDPOINT
import com.timkwali.weatherly.core.utils.Constants.DEFAULT_LOCATION_SEARCH_LIMIT
import com.timkwali.weatherly.core.utils.Constants.LOCATION_SEARCH_ENDPOINT
import com.timkwali.weatherly.core.utils.Constants.WEATHER_FORECAST_ENDPOINT
import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponse
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(CURRENT_WEATHER_ENDPOINT)
    suspend fun getCurrentWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String = API_KEY
    ): CurrentWeatherResponse

    @GET(WEATHER_FORECAST_ENDPOINT)
    suspend fun getWeatherForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherForecastResponse

    @GET(LOCATION_SEARCH_ENDPOINT)
    suspend fun searchLocations(
        @Query("q") searchQuery: String,
        @Query("limit") limit: String = DEFAULT_LOCATION_SEARCH_LIMIT,
        @Query("appid") apiKey: String = API_KEY
    ): LocationSearchResponse
}