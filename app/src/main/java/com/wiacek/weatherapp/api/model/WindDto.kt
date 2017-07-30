package com.wiacek.weatherapp.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */
data class WindDto(
        @SerializedName("speed")
        var speed: String = "",
        @SerializedName("deg")
        var direction: String = ""
)