package com.icarumbas.casto.di

import com.icarumbas.casto.api.icons.IconsApiImpl
import com.icarumbas.casto.api.icons.IconsApi
import com.icarumbas.casto.service.IconsService
import com.icarumbas.casto.storage.icons.IconsStorage
import com.icarumbas.casto.storage.icons.LocalIconsStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val iconsModule = DI.Module("iconsModule") {
    bind<IconsApi>() with singleton {
        IconsApiImpl(instance())
    }
    bind<IconsStorage>() with singleton {
        LocalIconsStorage(instance())
    }
    bind<IconsService>() with singleton {
        IconsService(
            iconsApi = instance(),
            iconsStorage = instance(),
        )
    }
}