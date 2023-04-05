package com.icarumbas.casto.di

import com.icarumbas.casto.Database
import com.icarumbas.casto.platformSpecific.data.SettingsFactory
import com.icarumbas.casto.platformSpecific.data.httpClient
import com.icarumbas.casto.storage.SecureKeyStorage
import com.icarumbas.casto.storage.createDatabase
import com.icarumbas.casto.storage.files.FileStorage
import com.icarumbas.casto.storage.files.OkioFileStorage
import com.russhwolf.settings.Settings
import io.ktor.client.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val coreApiModule = DI.Module("coreApiModule") {
    bind<HttpClient>() with singleton {
        httpClient()
    }
    bind<SecureKeyStorage>() with singleton {
        SecureKeyStorage()
    }
    bind<FileStorage>() with singleton {
        OkioFileStorage()
    }
    bind<Database>() with singleton {
        createDatabase(instance())
    }
    bind<Settings>() with singleton {
        val sf by appDI.instance<SettingsFactory>()
        sf.createSettings()
    }
}