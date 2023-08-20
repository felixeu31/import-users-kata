package com.kata.importusers.services

import com.kata.importusers.models.User

interface UserReader {
    fun getUsers(): List<User>
}
