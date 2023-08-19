package com.kata.importusers.interfaces

interface ApiClient {
    fun executeRequest(url: String): String
}
