package com.icarumbas.casto.di

import com.icarumbas.casto.storage.files.OkioFileStorage
import com.icarumbas.casto.storage.SecureKeyStorage
import com.icarumbas.casto.storage.files.FileStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val storageModule = DI.Module("storageModule") {
    bind<SecureKeyStorage>() with singleton {
        SecureKeyStorage()
    }
    bind<FileStorage>() with singleton {
        OkioFileStorage()
    }
}