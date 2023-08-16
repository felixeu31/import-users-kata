import io.mockk.every
import java.io.InputStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import io.mockk.mockk


class CsvUserReaderUnitTest {

    @Test
    fun should_return_empty_list_when_no_users_in_file() {
        // Given
        val fileStream: InputStream = """id,gender,name,country,postcode,email,birthdate""".byteInputStream()
        val fileReaderMock = mockk<FileReader>()
        every { fileReaderMock.getFileAsInputStream("mockPath") } returns fileStream
        val csvUserReader: CsvUserReader = CsvUserReader("mockPath", fileReaderMock)

        // When
        val users = csvUserReader.getUsers()

        // Then
        assertEquals(true, users.isEmpty())
    }

    @Test
    fun should_return_parsed_user_from_csv_file() {
        // Given
        val fileStream: InputStream = """id,gender,name,country,postcode,email,birthdate
200189617246,male,Lukas Schmidt,Germany,10780,lukas.shmidt@example.com,1997-02-19T04:10:00.000Z""".byteInputStream()
        val fileReaderMock = mockk<FileReader>()
        every { fileReaderMock.getFileAsInputStream("mockPath") } returns fileStream
        val csvUserReader: CsvUserReader = CsvUserReader("mockPath", fileReaderMock)

        // When
        val users = csvUserReader.getUsers()

        // Then
        assertEquals(false, users.isEmpty())
        assertEquals(1, users.size)
        assertEquals(200189617246, users.first().id)
        assertEquals("Germany", users.first().country)
        assertEquals("lukas.shmidt@example.com", users.first().email)
        assertEquals("Lukas Schmidt", users.first().name)
    }
}
