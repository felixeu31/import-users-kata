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
}
