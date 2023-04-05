package com.icarumbas.casto.di

import com.icarumbas.casto.api.user.UserApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val userModule = DI.Module("userModule") {
    bind<UserApi>() with singleton {
        UserApi(client = instance())
    }
}