package com.wiacek.weatherapp.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */

data class WeatherResponseDto(
        @SerializedName("weather")
        var weather: List<WeatherDto> = emptyList(),
        @SerializedName("main")
        var main: MainDto = MainDto(),
        @SerializedName("wind")
        var wind: WindDto = WindDto(),
        @SerializedName("uuid")
        var id: String = "",
        @SerializedName("name")
        var name: String = ""
)