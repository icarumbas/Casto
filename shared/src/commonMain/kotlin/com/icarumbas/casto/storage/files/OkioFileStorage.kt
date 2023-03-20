package com.icarumbas.casto.storage.files

import com.icarumbas.casto.platformSpecific.data.getApplicationFilesDirPath
import com.icarumbas.casto.platformSpecific.data.getApplicationFilesystem
import io.github.aakira.napier.Napier
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath



class OkioFileStorage : FileStorage {

    private val filesDir: Path
        get() = getApplicationFilesDirPath().toPath()

    private val fileSystem: FileSystem
        get() = getApplicationFilesystem()

    override fun createDirectory(path: String): Path? {
        val dirPath = filesDir.div(path)

        if (fileSystem.exists(dirPath)) {
            val meta = fileSystem.metadata(dirPath)
            if (meta.isDirectory) {
                return dirPath
            } else {
                Napier.e("Unable to create directory at existent file path: $dirPath")
                return null
            }
        } else {
            fileSystem.createDirectories(dirPath, true)
            return dirPath
        }
    }

    override fun getFilePath(path: String, directory: String): Path? {
        val dirPath = createDirectory(directory)
        if (dirPath != null) {
            return getFilePath(path, dirPath)
        } else {
            return null
        }
    }

    override fun getFilePath(path: String, directory: Path): Path {
        return directory.div(path.toPath())
    }

    override fun getFilePath(path: String): Path {
        return filesDir.div(path)
    }

    override fun exists(path: Path): Boolean {
        return fileSystem.exists(path)
    }

    override suspend fun write(path: Path, bytes: ByteArray, rewrite: Boolean) {
        fileSystem.write(path, rewrite) {
            write(bytes)
        }
    }

    override suspend fun read(path: Path): ByteArray {
        return fileSystem.read(path) {
            readByteArray()
        }
    }
}