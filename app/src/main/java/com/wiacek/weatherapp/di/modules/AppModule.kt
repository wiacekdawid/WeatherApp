package com.wiacek.weatherapp.di.modules

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import com.wiacek.weatherapp.WeatherApplication
import com.wiacek.weatherapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class AppModule(var application: WeatherApplication) {

    @Provides
    @Named("ApplicationContext")
    @ApplicationScope
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @ApplicationScope
    fun provideConnectivityManager(@Named("ApplicationContext") context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @ApplicationScope
    fun provideLocationManager(@Named("ApplicationContext") context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}