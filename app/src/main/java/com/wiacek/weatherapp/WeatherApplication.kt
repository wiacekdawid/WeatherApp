package com.wiacek.weatherapp

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import com.wiacek.weatherapp.data.db.Migration
import com.wiacek.weatherapp.data.db.ModelsRealmModule
import com.wiacek.weatherapp.di.components.AppComponent
import com.wiacek.weatherapp.di.components.DaggerAppComponent
import com.wiacek.weatherapp.di.modules.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

class WeatherApplication: MultiDexApplication() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var migration: Migration

    @Inject
    lateinit var modelsRealmModule: ModelsRealmModule

    val REALM_SCHEMA_VERSION = 1L

    companion object {
        fun get(context: Context) : WeatherApplication {
            return context.applicationContext as WeatherApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        initializeDI()
        initializeRealm()
        initializeLeakCanary()
        initializeStetho()
    }

    private fun initializeDI() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    private fun initializeLeakCanary() {
        if(!LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    private fun initializeRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .migration(migration)
                .modules(modelsRealmModule)
                .schemaVersion(REALM_SCHEMA_VERSION)
                .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun initializeStetho() {
        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                    .build())
                            .build())
        }
    }
}