package com.kata.importusers.infra

import com.kata.importusers.models.User

class UserPrinter {
    fun printUsers(users: List<User>) {
        println("*********************************************************************************")
        println("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
        println("*********************************************************************************")
        for (user in users) {
            println(String.format("* %s\t* %s\t\t* %s\t\t* %s\t*", user.id, user.country, user.name, user.email))
        }
        println("*********************************************************************************")
        println(users.size.toString() + " users in total!")
    }
}
