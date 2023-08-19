import io.mockk.every
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import io.mockk.mockk

class WebUserReaderUnitTest {

    @Test
    fun should_return_empty_list_when_api_returns_zero_results() {
        // Given
        val apiClientMock = mockk<ApiClient>()
        val responseMock = """{
	"results": [
		],
	"info": {
		"seed": "a9b25cd955e2037h",
		"results": 5,
		"page": 1,
		"version": "1.4"
	}
}"""
        every { apiClientMock.executeRequest("url mock") } returns responseMock
        val webUserReader = WebUserReader("url mock", apiClientMock)

        // When
        val users = webUserReader.getUsers()

        // Then
        assertEquals(true, users.isEmpty())
    }

    @Test
    fun should_return_parsed_users_from_api_response() {
        // Given
        val apiClientMock = mockk<ApiClient>()
        val responseMock = """{
	"results": [
		{
			"gender": "female",
			"name": {
				"title": "Mrs",
				"first": "Nevaeh",
				"last": "Dunn"
			},
			"location": {
				"street": {
					"number": 9249,
					"name": "Oak Lawn Ave"
				},
				"city": "Orange",
				"state": "Western Australia",
				"country": "Australia",
				"postcode": 5436,
				"coordinates": {
					"latitude": "-88.9811",
					"longitude": "59.7716"
				},
				"timezone": {
					"offset": "-7:00",
					"description": "Mountain Time (US & Canada)"
				}
			},
			"email": "nevaeh.dunn@example.com"
		},
		{
			"gender": "female",
			"name": {
				"title": "Miss",
				"first": "Sara",
				"last": "Abdallah"
			},
			"location": {
				"street": {
					"number": 7852,
					"name": "Lovisenlund"
				},
				"city": "Bleik",
				"state": "Buskerud",
				"country": "Norway",
				"postcode": "1409",
				"coordinates": {
					"latitude": "-68.0604",
					"longitude": "-173.8949"
				},
				"timezone": {
					"offset": "-11:00",
					"description": "Midway Island, Samoa"
				}
			},
			"email": "sara.abdallah@example.com"
		},
],
	"info": {
		"seed": "a9b25cd955e2037h",
		"results": 5,
		"page": 1,
		"version": "1.4"
	}
}"""
        every { apiClientMock.executeRequest("url mock") } returns responseMock
        val webUserReader = WebUserReader("url mock", apiClientMock)

        // When
        val users = webUserReader.getUsers()

        // Then
        assertEquals(false, users.isEmpty())
        assertEquals(2, users.size)
        assertEquals(100000000001, users.first().id)
        assertEquals("Australia", users.first().country)
        assertEquals("nevaeh.dunn@example.com", users.first().email)
        assertEquals("Nevaeh Dunn", users.first().name)
    }
}
