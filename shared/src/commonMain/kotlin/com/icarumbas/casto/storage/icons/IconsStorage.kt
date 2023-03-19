package com.icarumbas.casto.storage.icons

interface IconsStorage {

    fun save(ticker: String, data: ByteArray)

    fun read(ticker: String): ByteArray
}

class LocalIconsStorage : IconsStorage {

    override fun save(ticker: String, data: ByteArray) {
        // todo
    }

    override fun read(ticker: String): ByteArray {
        return ByteArray(0)
    }
}