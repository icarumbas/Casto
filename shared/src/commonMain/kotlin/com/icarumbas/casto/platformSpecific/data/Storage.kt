package com.icarumbas.casto.platformSpecific.data

import okio.FileSystem

expect fun getApplicationFilesDirPath(): String

expect fun getApplicationFilesystem(): FileSystem