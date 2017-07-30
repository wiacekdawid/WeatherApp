package com.wiacek.weatherapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wiacek.weatherapp.di.scopes.ApplicationScope
import com.wiacek.weatherapp.api.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class NetModule {
    val BASE_URL = "http://api.openweathermap.org/"

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient()
        return okHttpClient
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit
    }

    @Provides
    @ApplicationScope
    fun provideOpenWeatherMapService(retrofit: Retrofit): OpenWeatherMapService {
        return retrofit.create(OpenWeatherMapService::class.java)
    }
}