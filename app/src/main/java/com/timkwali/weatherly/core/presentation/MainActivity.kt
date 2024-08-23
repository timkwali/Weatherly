package com.timkwali.weatherly.core.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.timkwali.weatherly.core.presentation.theme.WeatherlyTheme
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.weather.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherlyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    val weatherViewModel = hiltViewModel<WeatherViewModel>()
//                    weatherViewModel.getCurrentWeather("44.34", "10.99")
                    weatherViewModel.getWeatherForecast("44.34", "10.99")
//                    weatherViewModel.searchLocations("london")
                    LaunchedEffect(key1 = "") {
                        lifecycleScope.launch {
                            weatherViewModel.currentWeatherState.collect {
                                if(it is Resource.Error) {
                                    Log.d("oiafjakf", "weather message--> ${it.message}")
                                }
                                Log.d("oiafjakf", "current weather--> ${it.data}")
                            }
                        }

                        lifecycleScope.launch {
                            weatherViewModel.weatherForecastState.collect {
                                if(it is Resource.Error) {
                                    Log.d("oiafjakf", "forecast message--> ${it.message}")
                                }
                                Log.d("oiafjakf", "weather forecast--> ${it.data}")
                            }
                        }

                        lifecycleScope.launch {
                            weatherViewModel.searchLocationState.collect {
                                if(it is Resource.Error) {
                                    Log.d("oiafjakf", "location message--> ${it.message}")
                                }
                                Log.d("oiafjakf", "location search--> ${it.data}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherlyTheme {
        Greeting("Android")
    }
}