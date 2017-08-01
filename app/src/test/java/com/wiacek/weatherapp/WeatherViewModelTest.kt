package com.wiacek.weatherapp

import com.wiacek.weatherapp.data.model.WeatherCondition
import com.wiacek.weatherapp.ui.weather.WeatherViewModel
import org.junit.Assert
import org.junit.Test

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherViewModelTest {

    var weatherViewModel: WeatherViewModel = WeatherViewModel()

    @Test
    fun shouldFillViewModelWithWeatherConditionData() {
        //given
        val weatherCondition = WeatherCondition(weatherDescription = "weatherDescription",
                                                temperature = "temperature",
                                                windSpeed = "windSpeed",
                                                windDirection = "windDirection",
                                                iconUrl = "iconUrl")
        //when
        weatherViewModel.fillViewModelData(weatherCondition)

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
        weatherViewModel.disableAllViews()

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
        weatherViewModel.showErrorMessage()

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
        weatherViewModel.showLoadingIndicator()

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
        weatherViewModel.hideLoadingIndicator()

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
        weatherViewModel.showOnlineData()

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
        weatherViewModel.showNoOfflineData()

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
        weatherViewModel.showOfflineData()

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

    @Test
    fun shouldShowOfflineMessage() {
        //given

        //when
        weatherViewModel.showOfflineMessage()

        //expect
        with(weatherViewModel) {
            Assert.assertEquals(isLoadingVisible, false)
            Assert.assertEquals(isErrorMessageVisible, false)
            Assert.assertEquals(isFabButtonVisible, true)
            Assert.assertEquals(isOfflineMessageVisible, true)
            Assert.assertEquals(isDataVisible, false)
            Assert.assertEquals(isLastUpdateDateVisible, false)
            Assert.assertEquals(isNoInternetInfoVisible, false)
            Assert.assertEquals(isScreenNoDataVisible, false)
        }
    }
}