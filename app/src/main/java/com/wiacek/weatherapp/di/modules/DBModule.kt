package com.wiacek.weatherapp.di.modules

import com.wiacek.weatherapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
class DBModule {
    @Provides
    @ApplicationScope
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }
}