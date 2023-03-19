package com.icarumbas.casto.platformSpecific.data

import okio.FileSystem
import platform.Foundation.NSHomeDirectory

actual fun getApplicationFilesDirPath(): String {
    return NSHomeDirectory()
}

actual fun getApplicationFilesystem(): FileSystem
    = FileSystem.SYSTEM