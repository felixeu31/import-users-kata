package com.kata.importusers.infra

interface ApiClient {
    fun executeRequest(url: String): String
}
