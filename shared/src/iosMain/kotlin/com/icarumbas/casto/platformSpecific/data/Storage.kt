package com.icarumbas.casto.platformSpecific.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import okio.FileSystem
import platform.Foundation.NSHomeDirectory
import com.icarumbas.casto.Database

actual fun getApplicationFilesDirPath(): String {
    return NSHomeDirectory()
}

actual fun getApplicationFilesystem(): FileSystem
    = FileSystem.SYSTEM

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "casto.db")
    }
}