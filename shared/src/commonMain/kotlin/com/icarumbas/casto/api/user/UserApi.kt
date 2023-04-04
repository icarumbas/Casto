package com.icarumbas.casto.api.user

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private const val BASE_URL = "https://casto.fly.dev/user"

class UserApi(
    private val client: HttpClient,
) {
    suspend fun getId(): String {
        val response = client.get("$BASE_URL/generate-id")
        return response.body()
    }
}