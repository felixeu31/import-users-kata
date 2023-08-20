package com.kata.importusers.infra

class ConsolePrinter: Printer {
    override fun writeLine(message: String) {
        println(message)
    }
}
