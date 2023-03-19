package com.icarumbas.casto.platformSpecific.data

import android.content.Context
import com.icarumbas.casto.di.appDI
import okio.FileSystem
import org.kodein.di.instance

actual fun getApplicationFilesDirPath(): String {
    val context: Context by appDI.instance()
    return context.filesDir.path
}

actual fun getApplicationFilesystem(): FileSystem
        = FileSystem.SYSTEM