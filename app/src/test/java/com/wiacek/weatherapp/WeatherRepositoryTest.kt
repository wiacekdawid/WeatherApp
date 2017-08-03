package com.wiacek.weatherapp

import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.data.model.WeatherCondition
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherRepositoryTest {
    @Mock
    lateinit var weatherRepository: WeatherRepository
    @Mock
    lateinit var weatherCondition: WeatherCondition

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}