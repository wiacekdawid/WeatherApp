package com.wiacek.weatherapp.data.db

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema
import javax.inject.Inject

/**
 * Created by wiacek.dawid@gmail.com
 */

open class Migration @Inject constructor() : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema: RealmSchema = realm.schema
    }
    private fun isUpdateToVersion(version: Long, oldVersion: Long, newVersion: Long): Boolean {
        return version in (oldVersion + 1)..newVersion
    }
}