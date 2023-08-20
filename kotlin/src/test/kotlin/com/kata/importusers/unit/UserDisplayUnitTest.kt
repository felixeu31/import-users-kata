package com.kata.importusers.unit

import com.kata.importusers.infra.Printer
import com.kata.importusers.models.User
import com.kata.importusers.services.UserDisplay
import io.mockk.*
import org.junit.jupiter.api.Test

class UserDisplayUnitTest {
    @Test
    fun testPrintUsers() {
        // Given
        val printerMock = mockk<Printer>()
        every { printerMock.writeLine(any()) } just Runs
        val userPrinter = UserDisplay(printerMock)
        val users = listOf(
            User(1, "john@example.com", "John Doe", "USA"),
            User(2, "jane@example.com", "Jane Smith", "Canada")
        )

        // When
        userPrinter.printUsers(users)

        // Then
        verifySequence {
            printerMock.writeLine("*********************************************************************************")
            printerMock.writeLine("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
            printerMock.writeLine("*********************************************************************************")
            printerMock.writeLine("* 1\t* USA\t\t* John Doe\t\t* john@example.com\t*")
            printerMock.writeLine("* 2\t* Canada\t\t* Jane Smith\t\t* jane@example.com\t*")
            printerMock.writeLine("*********************************************************************************")
            printerMock.writeLine("2 users in total!")
        }
    }

}
