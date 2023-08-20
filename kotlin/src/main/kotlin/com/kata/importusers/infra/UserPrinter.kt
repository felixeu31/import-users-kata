package com.kata.importusers.infra

import com.kata.importusers.interfaces.Printer
import com.kata.importusers.models.User

class UserPrinter(private val printer: Printer) {
    fun printUsers(users: List<User>) {
        printer.writeLine("*********************************************************************************")
        printer.writeLine("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
        printer.writeLine("*********************************************************************************")
        for (user in users) {
            printer.writeLine(String.format("* %s\t* %s\t\t* %s\t\t* %s\t*", user.id, user.country, user.name, user.email))
        }
        printer.writeLine("*********************************************************************************")
        printer.writeLine(users.size.toString() + " users in total!")
    }
}
