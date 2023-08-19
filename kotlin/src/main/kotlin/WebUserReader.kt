import org.json.JSONObject
import java.math.BigInteger
import java.util.*

class WebUserReader constructor(private val url: String, private val apiClient: ApiClient): UserReader {
    override fun getUsers(): List<User> {
        val webUsers = ArrayList<Array<String>>()
        val usersResponse = apiClient.executeRequest(url)


        val jsonObject = JSONObject(usersResponse)
        val results = jsonObject.getJSONArray("results")
        var j = BigInteger("100000000000")
        for (i in 0 until results.length()) {
            j = j.add(BigInteger("1"))
            webUsers.add(
                arrayOf(
                    j.toString(),  // id
                    results.getJSONObject(i).getString("gender"),
                    results.getJSONObject(i).getJSONObject("name").getString("first") + " " + results.getJSONObject(i)
                        .getJSONObject("name").getString("last"),
                    results.getJSONObject(i).getJSONObject("location").getString("country"),
                    results.getJSONObject(i).getJSONObject("location")["postcode"].toString(),
                    results.getJSONObject(i).getString("email"),
                    GregorianCalendar()[Calendar.YEAR].toString()
                )
            )
        }
        return webUsers.map { rawUser ->
            User(
                rawUser[0].toLong(),
                rawUser[5],
                rawUser[2],
                rawUser[3],
            )
        }
    }
}
