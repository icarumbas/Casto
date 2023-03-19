package com.icarumbas.casto.platformSpecific.data

import platform.Foundation.NSHomeDirectory

actual fun getFilesDirPath(): String {
    return NSHomeDirectory()
}