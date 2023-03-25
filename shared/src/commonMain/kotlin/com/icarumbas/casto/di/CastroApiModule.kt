package com.icarumbas.casto.di

import com.icarumbas.casto.api.icons.CastoIconsApi
import com.icarumbas.casto.api.icons.IconsApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val castoApiModule = DI.Module("castoApiModule") {
    bind<IconsApi>() with singleton {
        CastoIconsApi(instance())
    }
}