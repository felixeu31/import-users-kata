package com.kata.importusers.infra

import java.util.*

class PublicApiClient: ApiClient {
    override fun executeRequest(url: String): String {
        // Parse URL content
        var result = ""
        val command = "curl -X GET $url"
        val processBuilder = ProcessBuilder(command.split(" "))
        val process = processBuilder.start()
        val iStream = process.inputStream
        val webProvider = Scanner(iStream)
        while (webProvider.hasNextLine()) {
            result += webProvider.nextLine()
        }
        webProvider.close()
        return result
    }
}
