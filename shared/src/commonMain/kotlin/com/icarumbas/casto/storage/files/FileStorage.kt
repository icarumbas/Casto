package com.icarumbas.casto.storage.files

import okio.Path

interface FileStorage {

    fun createDirectory(path: String): Path?

    fun getFilePath(path: String, directory: String): Path?
    
    fun getFilePath(path: String, directory: Path): Path

    fun getFilePath(path: String): Path
    
    fun exists(path: Path): Boolean
    
    suspend fun write(path: Path, bytes: ByteArray, rewrite: Boolean = true)
    
    suspend fun read(path: Path): ByteArray
}