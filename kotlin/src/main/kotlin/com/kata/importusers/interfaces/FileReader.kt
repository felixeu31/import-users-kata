package com.kata.importusers.interfaces

import java.io.InputStream

interface FileReader {
    fun getFileAsInputStream(filePath: String): InputStream
}
