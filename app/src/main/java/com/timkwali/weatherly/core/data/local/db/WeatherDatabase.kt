package com.timkwali.weatherly.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timkwali.weatherly.weather.data.local.dao.WeatherDao
import com.timkwali.weatherly.core.utils.Constants.WEATHER_DATABASE
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState

@Database(
    entities = [CurrentWeatherState::class, WeatherForecastState::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = WEATHER_DATABASE
    }
}