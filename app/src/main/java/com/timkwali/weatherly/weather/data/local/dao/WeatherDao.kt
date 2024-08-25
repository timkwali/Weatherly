package com.timkwali.weatherly.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherState)

    @Query("SELECT * FROM current_weather_table")
    fun getDbCurrentWeather(): Flow<CurrentWeatherState>

    @Query("DELETE FROM current_weather_table")
    suspend fun clearCurrentWeather()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecast: List<WeatherForecastState>)

    @Query("SELECT * FROM weather_forecast_table")
    fun getDbWeatherForecast(): Flow<List<WeatherForecastState>>

    @Query("DELETE FROM weather_forecast_table")
    suspend fun clearWeatherForecast()
}