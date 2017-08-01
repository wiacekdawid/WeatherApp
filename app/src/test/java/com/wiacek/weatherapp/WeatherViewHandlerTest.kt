package com.wiacek.weatherapp

import android.location.LocationManager
import android.net.ConnectivityManager
import com.nhaarman.mockito_kotlin.description
import com.wiacek.weatherapp.data.WeatherRepository
import com.wiacek.weatherapp.data.model.WeatherCondition
import com.wiacek.weatherapp.ui.weather.WeatherViewHandler
import com.wiacek.weatherapp.ui.weather.WeatherViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherViewHandlerTest {

    @Mock
    lateinit var weatherRepository: WeatherRepository
    @Mock
    lateinit var connectivityManager: ConnectivityManager
    @Mock
    lateinit var locationManager: LocationManager

    lateinit var weatherViewHandler: WeatherViewHandler

    var weatherViewModel: WeatherViewModel = WeatherViewModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        weatherViewHandler = WeatherViewHandler(weatherViewModel = weatherViewModel,
                                                weatherRepository = weatherRepository,
                                                connectivityManager = connectivityManager,
                                                locationManager = locationManager)
    }

    @Test
    fun shouldFillViewModelWithWeatherConditionData() {
        //given
        val weatherCondition = WeatherCondition(weatherDescription = "weatherDescription",
                                                temperature = "temperature",
                                                windSpeed = "windSpeed",
                                                windDirection = "windDirection",
                                                iconUrl = "iconUrl")
        //when
        weatherViewHandler.fillViewModelData(weatherCondition)

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(currentCondition, "weatherDescription")
            Assert.assertEquals(iconUrl, "iconUrl")
            Assert.assertEquals(temperature, "temperature" + " \u2103")
            Assert.assertEquals(windSpeed, "windSpeed")
            Assert.assertEquals(windDirection, "windDirection")
        }
    }

    @Test
    fun shouldDisableAllViews() {
        //given

        //when
        weatherViewHandler.disableAllViews()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, false)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }

    @Test
    fun shouldShowErrorMessageWithFabButton() {
        //given

        //when
        weatherViewHandler.showErrorMessage()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isErrorMessageVisible, true)
            Assert.assertEquals(isFabButtonVisible, true)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }

    @Test
    fun shouldShowLoadingIndicator() {
        //given

        //when
        weatherViewHandler.showLoadingIndicator()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, true)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, false)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }

    @Test
    fun shouldHideLoadingIndicator() {
        //given

        //when
        weatherViewHandler.hideLoadingIndicator()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, false)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, false)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }

    @Test
    fun shouldShowOnlineData() {
        //given

        //when
        weatherViewHandler.showOnlineData()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, false)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, false)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, true)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }

    @Test
    fun shouldShowNoOfflineData() {
        //given

        //when
        weatherViewHandler.showNoOfflineData()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, false)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, true)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, true)
        }
    }

    @Test
    fun shouldShowOfflineData() {
        //given

        //when
        weatherViewHandler.showOfflineData()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, false)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, true)
            Assert.assertEquals(isOfflineMessageVisible, false)
            Assert.assertEquals(isDataVisible, true)
            Assert.assertEquals(isLastUpdateDateVisible, true)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }
}