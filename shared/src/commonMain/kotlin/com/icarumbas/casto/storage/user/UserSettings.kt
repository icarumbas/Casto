package com.icarumbas.casto.storage.user

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class UserSettings(private val settings: Settings) {

    var id: String
        get() = settings.getString(ID, "")
        set(value) {
            settings[ID] = value
        }

    var binanceSetUp: Boolean
        get() = settings.getBoolean(BINANCE, false)
        set(value) {
            settings.set(BINANCE, value)
        }

    private companion object {
        const val ID = "ID"
        const val BINANCE = "BINANCE"
    }
}