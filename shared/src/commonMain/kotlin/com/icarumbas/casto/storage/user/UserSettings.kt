package com.icarumbas.casto.storage.user

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class UserSettings(private val settings: Settings) {

    var id: String
        get() = settings.getString(ID, "")
        set(value) {
            settings[ID] = value
        }

    private companion object {
        const val ID = "ID"
    }
}