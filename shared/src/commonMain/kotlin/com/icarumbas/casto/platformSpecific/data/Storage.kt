package com.icarumbas.casto.platformSpecific.data

import app.cash.sqldelight.db.SqlDriver
import okio.FileSystem

expect fun getApplicationFilesDirPath(): String

expect fun getApplicationFilesystem(): FileSystem

expect class DriverFactory {
    fun createDriver(): SqlDriver
}