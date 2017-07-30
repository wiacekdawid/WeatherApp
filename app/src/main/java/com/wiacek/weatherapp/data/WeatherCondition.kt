package com.wiacek.weatherapp.data

/**
 * Created by wiacek.dawid@gmail.com
 */

data class WeatherCondition(val weatherDescription: String = "",
                            val temperature: String = "",
                            val windSpeed: String = "",
                            val windDirection: String = "",
                            val iconUrl: String = "")