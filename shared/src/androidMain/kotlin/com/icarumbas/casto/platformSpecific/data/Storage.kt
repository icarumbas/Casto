package com.icarumbas.casto.platformSpecific.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.icarumbas.casto.Database
import com.icarumbas.casto.di.appDI
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import okio.FileSystem
import org.kodein.di.instance

actual fun getApplicationFilesDirPath(): String {
    val context: Context by appDI.instance()
    return context.filesDir.path
}

actual fun getApplicationFilesystem(): FileSystem
        = FileSystem.SYSTEM

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "casto.db")
    }
}

actual class SettingsFactory(private val context: Context) {

    actual fun createSettings(): Settings {
        val delegate = context.getSharedPreferences("casto-settings", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate)
    }
}