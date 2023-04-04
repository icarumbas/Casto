package com.icarumbas.casto.api.models

@kotlinx.serialization.Serializable
data class KeyCredentialsRequest(
    val publicKey: String,
    val privateKey: String,
)