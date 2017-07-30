package com.wiacek.weatherapp.data

import com.wiacek.weatherapp.api.model.WeatherResponseDto

/**
 * Created by wiacek.dawid@gmail.com
 */
object WeatherConditionMapper {
    fun transformWeatherResponseDtoToWeatherCondition(weatherResponseDto: WeatherResponseDto)
            : WeatherCondition {
        return WeatherCondition(weatherDescription = weatherResponseDto.weather[0].main,
                temperature = weatherResponseDto.main.temperature,
                windSpeed = weatherResponseDto.wind.speed,
                windDirection = weatherResponseDto.wind.direction)
    }
}