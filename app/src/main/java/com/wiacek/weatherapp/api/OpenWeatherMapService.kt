package com.wiacek.weatherapp.api

import com.wiacek.weatherapp.api.model.WeatherResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wiacek.dawid@gmail.com
 */

interface OpenWeatherMapService {
    @GET("data/2.5/weather")
    fun getWeatherConditionByLatLon(@Query("lat")lat: Double,
                                    @Query("lon")lon: Double,
                                    @Query("APPID")apiid: String): Single<WeatherResponseDto>
}