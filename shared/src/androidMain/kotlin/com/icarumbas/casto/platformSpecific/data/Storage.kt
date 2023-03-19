package com.icarumbas.casto.platformSpecific.data

import android.content.Context
import com.icarumbas.casto.di.appDI
import org.kodein.di.instance

actual fun getFilesDirPath(): String {
    val context: Context by appDI.instance()
    return context.filesDir.path
}