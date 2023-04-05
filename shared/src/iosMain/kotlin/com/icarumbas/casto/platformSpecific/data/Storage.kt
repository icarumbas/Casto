package com.icarumbas.casto.platformSpecific.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import okio.FileSystem
import platform.Foundation.NSHomeDirectory
import com.icarumbas.casto.Database
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun getApplicationFilesDirPath(): String {
    return NSHomeDirectory()
}

actual fun getApplicationFilesystem(): FileSystem
    = FileSystem.SYSTEM

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "casto.db")
    }
}

actual class SettingsFactory {

    actual fun createSettings(): Settings {
        val delegate = NSUserDefaults()
        return NSUserDefaultsSettings(delegate)
    }
}