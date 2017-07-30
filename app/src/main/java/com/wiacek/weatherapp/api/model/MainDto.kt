package com.wiacek.weatherapp.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wiacek.dawid@gmail.com
 */
data class MainDto(
        @SerializedName("temp")
        var temperature: String = ""
)