package com.wiacek.weatherapp

import android.location.Location
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.wiacek.weatherapp.data.WeatherDataManager
import com.wiacek.weatherapp.data.model.WeatherCondition
import com.wiacek.weatherapp.ui.weather.AttachedWeatherActivity
import com.wiacek.weatherapp.ui.weather.WeatherViewHandler
import com.wiacek.weatherapp.ui.weather.WeatherViewModel
import com.wiacek.weatherapp.util.NetworkManager
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherViewHandlerTest {

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var weatherRepository: WeatherDataManager
    @Mock
    lateinit var networkManager: NetworkManager
    @Mock
    lateinit var weatherViewModel: WeatherViewModel
    @Mock
    lateinit var location: Location
    @Mock
    lateinit var attachedWeatherActivity: AttachedWeatherActivity

    lateinit var weatherViewHandler: WeatherViewHandler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        weatherViewHandler = WeatherViewHandler(attachedWeatherActivity = attachedWeatherActivity,
                weatherViewModel = weatherViewModel,
                weatherDataManager = weatherRepository,
                networkManager = networkManager)
    }

    @Test
    fun shouldShowOfflineMessageWhenRefreshManually() {
        //given
        Mockito.`when`(networkManager.isInternetOn()).thenReturn(false)

        //when
        weatherViewHandler.refreshManually()

        //expect
        verify(weatherViewModel, times(1)).showOfflineMessage()
        verify(weatherViewModel, times(1)).disableAllViews()
    }

    @Test
    fun shouldGetWeatherConditionByLatLonRemoteWhenRefresh() {
        //given
        Mockito.`when`(networkManager.isInternetOn()).thenReturn(true)
        Mockito.`when`(attachedWeatherActivity.getLocation()).thenReturn(location)
        Mockito.`when`(weatherRepository.getWeatherConditionByLatLonRemote(0.0, 0.0)).thenReturn(Single.just(WeatherCondition()))

        //when
        weatherViewHandler.refreshWeatherConditions()

        //expect
        verify(weatherRepository, times(1)).getWeatherConditionByLatLonRemote(0.0, 0.0)
    }

    @Test
    fun shouldGetLatestWeatherConditionLocalWhenRefreshNoLocationNoInternet() {
        //given
        Mockito.`when`(networkManager.isInternetOn()).thenReturn(false)
        Mockito.`when`(attachedWeatherActivity.getLocation()).thenReturn(null)
        Mockito.`when`(weatherRepository.getLatestWeatherConditionLocal()).thenReturn(Maybe.empty())

        //when
        weatherViewHandler.refreshWeatherConditions()

        //expect
        verify(weatherRepository, times(1)).getLatestWeatherConditionLocal()
    }

    @Test
    fun shouldGetLatestWeatherConditionLocalWhenRefreshNoLocation() {
        //given
        Mockito.`when`(networkManager.isInternetOn()).thenReturn(true)
        Mockito.`when`(attachedWeatherActivity.getLocation()).thenReturn(null)
        Mockito.`when`(weatherRepository.getLatestWeatherConditionLocal()).thenReturn(Maybe.empty())

        //when
        weatherViewHandler.refreshWeatherConditions()

        //expect
        verify(weatherRepository, times(1)).getLatestWeatherConditionLocal()
    }

    @Test
    fun shouldGetLatestWeatherConditionLocalWhenRefreshNoInternet() {
        //given
        Mockito.`when`(networkManager.isInternetOn()).thenReturn(false)
        Mockito.`when`(attachedWeatherActivity.getLocation()).thenReturn(location)
        Mockito.`when`(weatherRepository.getLatestWeatherConditionLocal()).thenReturn(Maybe.empty())

        //when
        weatherViewHandler.refreshWeatherConditions()

        //expect
        verify(weatherRepository, times(1)).getLatestWeatherConditionLocal()
    }
}