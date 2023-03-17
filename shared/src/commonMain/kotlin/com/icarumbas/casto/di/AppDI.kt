package com.icarumbas.casto.di

import org.kodein.di.DI

val appDI = DI {
    importAll(repositoriesModule)
}