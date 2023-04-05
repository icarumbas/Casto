package com.icarumbas.casto.storage

import com.icarumbas.casto.Database
import com.icarumbas.casto.platformSpecific.data.DatabaseDriverFactory

fun createDatabase(databaseDriverFactory: DatabaseDriverFactory): Database {
    val driver = databaseDriverFactory.createDriver()
    return Database(driver)
}