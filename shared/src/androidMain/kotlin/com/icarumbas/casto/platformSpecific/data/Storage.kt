package com.icarumbas.casto.platformSpecific.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.icarumbas.casto.di.appDI
import okio.FileSystem
import org.kodein.di.instance
import com.icarumbas.casto.Database

actual fun getApplicationFilesDirPath(): String {
    val context: Context by appDI.instance()
    return context.filesDir.path
}

actual fun getApplicationFilesystem(): FileSystem
        = FileSystem.SYSTEM

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "casto.db")
    }
}