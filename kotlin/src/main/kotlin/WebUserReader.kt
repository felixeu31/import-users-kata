import org.json.JSONArray
import org.json.JSONObject

class WebUserReader constructor(private val url: String, private val apiClient: ApiClient): UserReader {
    override fun getUsers(): List<User> {
        val usersResponse = apiClient.executeRequest(url)

        val results = JSONObject(usersResponse).getJSONArray("results")
        return parseJsonResponse(results)
    }

    private fun parseJsonResponse(results: JSONArray): List<User> {
        val users = mutableListOf<User>()
        var id: Long = 100000000000
        for (i in 0 until results.length()) {
            users.add(
                User(
                    ++id,
                    results.getJSONObject(i).getString("email"),
                    results.getJSONObject(i).getJSONObject("name").getString("first") + " " + results.getJSONObject(i)
                        .getJSONObject("name").getString("last"),
                    results.getJSONObject(i).getJSONObject("location").getString("country")
                )
            )
        }
        return users
    }
}
