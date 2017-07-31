package com.wiacek.weatherapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wiacek.weatherapp.BuildConfig
import com.wiacek.weatherapp.di.scopes.ApplicationScope
import com.wiacek.weatherapp.api.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

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
    @Named("OpenWeatherMapInterceptor")
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor {
            val original = it.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("APPID", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    // by setting units = metric we get temperature in Celcius degrees
                    .addQueryParameter("units", "metric")
                    .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            it.proceed(request)
        }
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClientBuilder(
            @Named("OpenWeatherMapInterceptor")interceptor: Interceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(interceptor)
    }


    @Provides
    @ApplicationScope
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder.build()
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