import org.json.JSONObject
import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayList

class WebUserReader constructor(private val url: String): UserReader {
    override fun getUsers(): ArrayList<Array<String>> {
        // Parse URL content
        val webUsers = java.util.ArrayList<Array<String>>()
        val command = "curl -X GET " + url
        val processBuilder = ProcessBuilder(command.split(" "))
        val process = processBuilder.start()
        val iStream = process.inputStream
        val webProvider = Scanner(iStream)
        var result = ""
        while (webProvider.hasNextLine()) {
            result += webProvider.nextLine()
        }
        webProvider.close()

        val jsonObject = JSONObject(result)
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
        return webUsers
    }
}
