package com.wiacek.weatherapp

import com.wiacek.weatherapp.api.model.MainDto
import com.wiacek.weatherapp.api.model.WeatherDto
import com.wiacek.weatherapp.api.model.WeatherResponseDto
import com.wiacek.weatherapp.api.model.WindDto
import com.wiacek.weatherapp.data.model.WeatherConditionMapper
import org.junit.Assert
import org.junit.Test

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherConditionMapperTest {

    @Test
    fun fromWeatherResponseDtoToWeatherCondition() {
        //given
        val weatherResponseDto = WeatherResponseDto(
                main = MainDto(temperature = "temperature"),
                wind = WindDto(speed = "speed", direction = "direction"),
                weather = listOf(WeatherDto(main = "main", icon = "icon")),
                id = "id",
                name = "name"
        )

        //when
        val weatherCondition = WeatherConditionMapper.transformWeatherResponseDtoToWeatherCondition(weatherResponseDto)

        //then
        with(weatherCondition) {
            Assert.assertEquals(weatherDescription, "main")
            Assert.assertEquals(weatherCondition.iconUrl, "http://openweathermap.org/img/w/icon.png")
            Assert.assertEquals(temperature, "temperature")
            Assert.assertEquals(windSpeed, "speed")
            Assert.assertEquals(windDirection, "direction")
            Assert.assertEquals(name, "name")
            Assert.assertEquals(uuid, "id")
        }
    }
}