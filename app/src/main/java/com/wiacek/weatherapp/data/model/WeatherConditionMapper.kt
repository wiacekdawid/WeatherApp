package com.wiacek.weatherapp.data.model

import com.wiacek.weatherapp.api.model.WeatherResponseDto

/**
 * Created by wiacek.dawid@gmail.com
 */
object WeatherConditionMapper {
    fun transformWeatherResponseDtoToWeatherCondition(weatherResponseDto: WeatherResponseDto)
            : WeatherCondition {
        return WeatherCondition(uuid = weatherResponseDto.id,
                name = weatherResponseDto.name,
                weatherDescription = weatherResponseDto.weather[0].main,
                temperature = weatherResponseDto.main.temperature,
                windSpeed = weatherResponseDto.wind.speed,
                windDirection = weatherResponseDto.wind.direction,
                iconUrl = "http://openweathermap.org/img/w/" + weatherResponseDto.weather[0].icon + ".png")
    }
}