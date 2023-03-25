package com.icarumbas.casto.api.icons

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

private const val ICONS_BASE_URL = "https://casto.fly.dev/icons"

class CastoIconsApi(
    private val client: HttpClient
) : IconsApi {

    override suspend fun getIcon(ticker: String): ByteArray? {
        val response = client.get(ICONS_BASE_URL) {
            url.appendEncodedPathSegments(ticker)
        }
        if (response.status.isSuccess()) {
            return response.bodyAsChannel().toByteArray()
        } else {
            return null
        }
    }
}