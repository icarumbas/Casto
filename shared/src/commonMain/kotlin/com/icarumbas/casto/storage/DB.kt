package com.icarumbas.casto.storage

import com.icarumbas.casto.Database
import com.icarumbas.casto.platformSpecific.data.DriverFactory

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}