package com.kata.importusers.unit

import com.kata.importusers.interfaces.FileReader
import com.kata.importusers.readers.CsvUserReader
import io.mockk.every
import java.io.InputStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import io.mockk.mockk

class CsvUserReaderUnitTest {

    @Test
    fun should_return_empty_list_when_no_users_in_file() {
        // Given
        val fileReaderMock = mockk<FileReader>()
        val fileContentMock = """id,gender,name,country,postcode,email,birthdate"""
        val fileStream: InputStream = fileContentMock.byteInputStream()
        every { fileReaderMock.getFileAsInputStream("mockPath") } returns fileStream
        val csvUserReader = CsvUserReader("mockPath", fileReaderMock)

        // When
        val users = csvUserReader.getUsers()

        // Then
        assertEquals(true, users.isEmpty())
    }

    @Test
    fun should_return_parsed_users_from_csv_file() {
        // Given
        val fileReaderMock = mockk<FileReader>()
        val fileContentMock = """id,gender,name,country,postcode,email,birthdate
200189617246,male,Lukas Schmidt,Germany,10780,lukas.shmidt@example.com,1997-02-19T04:10:00.000Z
200189016257,female,Maria Fischer,Germany,15010,maria.fischer@example.com,1991-08-06T09:20:00.000Z"""
        val fileStream: InputStream = fileContentMock.byteInputStream()
        every { fileReaderMock.getFileAsInputStream("mockPath") } returns fileStream
        val csvUserReader = CsvUserReader("mockPath", fileReaderMock)

        // When
        val users = csvUserReader.getUsers()

        // Then
        assertEquals(false, users.isEmpty())
        assertEquals(2, users.size)
        assertEquals(200189617246, users.first().id)
        assertEquals("Germany", users.first().country)
        assertEquals("lukas.shmidt@example.com", users.first().email)
        assertEquals("Lukas Schmidt", users.first().name)
    }
}
