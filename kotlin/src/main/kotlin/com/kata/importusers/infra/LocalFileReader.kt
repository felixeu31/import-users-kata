package com.kata.importusers.infra

import com.kata.importusers.interfaces.FileReader
import java.io.InputStream

class LocalFileReader: FileReader {
    override fun getFileAsInputStream(filePath: String): InputStream {
        val classLoader = LocalFileReader::class.java.classLoader
        val stream = classLoader.getResourceAsStream(filePath)
        return stream
    }
}
