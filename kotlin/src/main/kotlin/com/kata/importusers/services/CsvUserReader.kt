package com.kata.importusers.services

import com.kata.importusers.infra.FileReader
import com.kata.importusers.models.User
import java.util.*

class CsvUserReader constructor(private val csvSource: String, private val fileReader: FileReader): UserReader {
    override fun getUsers(): List<User> {
        val users: MutableList<User> = mutableListOf()

        val fileScanner = Scanner(fileReader.getFileAsInputStream(csvSource))

        // Skip header
        fileScanner.nextLine()

        while (fileScanner.hasNextLine()) {
            val user = parseLineToUser(fileScanner.nextLine())

            if(user != null)
                users.add(user)
        }

        return users
    }

    private fun parseLineToUser(line: String): User? {
        val values = line.split(",")

        if(values.isEmpty())
            return null

        return User(
            values[0].toLong(),
            values[5],
            values[2],
            values[3],
        )
    }
}
