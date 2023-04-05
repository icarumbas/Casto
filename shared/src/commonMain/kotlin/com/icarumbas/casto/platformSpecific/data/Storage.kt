package com.icarumbas.casto.platformSpecific.data

import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings
import okio.FileSystem

expect fun getApplicationFilesDirPath(): String

expect fun getApplicationFilesystem(): FileSystem

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect class SettingsFactory {

    fun createSettings(): Settings
}