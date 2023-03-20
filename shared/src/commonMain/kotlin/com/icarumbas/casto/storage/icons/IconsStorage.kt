package com.icarumbas.casto.storage.icons

import com.icarumbas.casto.storage.files.FileStorage
import okio.Path

interface IconsStorage {

    suspend fun save(ticker: String, data: ByteArray)

    suspend fun read(ticker: String): ByteArray

    fun exists(ticker: String): Boolean

    fun getRelativePath(ticker: String): String
}

private const val ICONS_DIR_NAME = "icons"

class LocalIconsStorage(
    private val fileStorage: FileStorage
) : IconsStorage {

    private val iconsDir: Path =
        requireNotNull(fileStorage.createDirectory(ICONS_DIR_NAME))

    private fun iconPath(ticker: String): Path =
        fileStorage.getFilePath("$ticker.png", iconsDir)

    override suspend fun save(ticker: String, data: ByteArray) {
        val iconPath = iconPath(ticker)
        fileStorage.write(iconPath, data)
    }

    override suspend fun read(ticker: String): ByteArray {
        val iconPath = iconPath(ticker)
        return fileStorage.read(iconPath)
    }

    override fun exists(ticker: String): Boolean {
        val iconPath = iconPath(ticker)
        return fileStorage.exists(iconPath)
    }

    override fun getRelativePath(ticker: String): String {
        return iconPath(ticker).toString()
    }
}