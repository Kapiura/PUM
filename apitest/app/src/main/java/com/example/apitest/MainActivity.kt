package com.example.apitest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apitest.ui.theme.ApitestTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API Interface
interface WeatherApi {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>
}

// Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}

// Data Classes
data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    val wind: Wind?,
    val clouds: Clouds?,
    val snow: Snow?
)

data class Main(
    val temp: Double,
    val humidity: Int,
    val pressure: Int,
    val temp_min: Double,
    val temp_max: Double
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class Wind(val speed: Double, val deg: Int)
data class Clouds(val all: Int)
data class Snow(val `1h`: Double?)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApitestTheme {
                WeatherScreen()
            }
        }
    }
}

@Composable
fun WeatherScreen() {
    var city by remember { mutableStateOf("Warsaw") }
    var weatherInfo by remember { mutableStateOf("Enter city name and press Get Weather") }
    val apiKey = "7b7fe4dd87c83d143654327eaa81fdd8"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            RetrofitInstance.api.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weather = response.body()
                        if (weather != null) {
                            weatherInfo = """
                                City: ${weather.name}
                                Temp: ${weather.main.temp}°C
                                Min Temp: ${weather.main.temp_min}°C
                                Max Temp: ${weather.main.temp_max}°C
                                Description: ${weather.weather[0].description}
                                Humidity: ${weather.main.humidity}%
                                Wind: ${weather.wind?.speed} m/s
                            """.trimIndent()
                        } else {
                            weatherInfo = "No data available"
                        }
                    } else {
                        weatherInfo = "Error: ${response.code()} - ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("WeatherApp", "Error: ${t.localizedMessage}")
                    weatherInfo = "Failure: ${t.localizedMessage}"
                }
            })
        }) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = weatherInfo, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApitestTheme {
        WeatherScreen()
    }
}
