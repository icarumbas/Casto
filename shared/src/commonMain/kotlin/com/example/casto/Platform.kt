package com.example.casto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform