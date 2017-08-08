package com.wiacek.weatherapp

import com.wiacek.weatherapp.data.WeatherDataManager
import com.wiacek.weatherapp.data.model.WeatherCondition
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherDataManagerTest {
    @Mock
    lateinit var weatherRepository: WeatherDataManager
    @Mock
    lateinit var weatherCondition: WeatherCondition

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}