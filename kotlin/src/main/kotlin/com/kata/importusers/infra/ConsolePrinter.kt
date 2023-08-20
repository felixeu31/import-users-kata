package com.kata.importusers.infra

import com.kata.importusers.interfaces.Printer

class ConsolePrinter: Printer {
    override fun writeLine(message: String) {
        println(message)
    }
}
