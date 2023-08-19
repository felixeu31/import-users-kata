package com.kata.importusers.readers

import com.kata.importusers.models.User

interface UserReader {
    fun getUsers(): List<User>
}
