package com.wiacek.weatherapp.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */
data class WeatherDto(
        @SerializedName("main")
        var main: String = "",
        @SerializedName("icon")
        var icon: String = ""
)