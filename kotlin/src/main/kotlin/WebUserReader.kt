import org.json.JSONObject

class WebUserReader constructor(private val url: String, private val apiClient: ApiClient): UserReader {
    override fun getUsers(): List<User> {
        val users = mutableListOf<User>()
        val usersResponse = apiClient.executeRequest(url)
        var id: Long = 100000000000

        val results = JSONObject(usersResponse).getJSONArray("results")
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
