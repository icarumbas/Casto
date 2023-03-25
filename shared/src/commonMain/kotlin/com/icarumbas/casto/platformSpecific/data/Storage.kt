package com.icarumbas.casto.platformSpecific.data

import app.cash.sqldelight.db.SqlDriver
import okio.FileSystem
import com.icarumbas.casto.Database

expect fun getApplicationFilesDirPath(): String

expect fun getApplicationFilesystem(): FileSystem

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}