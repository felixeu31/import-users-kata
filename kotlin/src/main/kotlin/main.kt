import org.json.JSONObject
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h"

    val classLoader = object {}.javaClass
    val stream = classLoader.getResourceAsStream("users.csv")
    val users: ArrayList<Array<String>> = ArrayList()
    val csvFile: Scanner = Scanner(stream)
    while (csvFile.hasNextLine()) {
        val line = csvFile.nextLine()
        // fields: ID, gender, Name ,country, postcode, email, Birthdate
        val attributes: Array<String> = line.split(",").toTypedArray()
        if (attributes.size == 0) {
            continue;
        }
        users.add(attributes)
    }

    users.removeAt(0) // Remove header column

    // Parse URL content
    val url = USER_URL;
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
    val b = ArrayList<Array<String>>()
    for (i in 0 until results.length()) {
        j = j.add(BigInteger("1"))
        b.add(
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

    /**
     * csv_providers ArrayList<id: number,
     *       email: string
     *       first_name: string
     *       last_name: string>
     */
    users.addAll(b)

    println("*********************************************************************************")
    println("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
    println("*********************************************************************************")
    for (user in users) {
        println(String.format("* %s\t* %s\t\t* %s\t\t* %s\t*", user[0], user[3], user[2], user[5]))
    }
    println("*********************************************************************************")
    println(users.size.toString() + " users in total!")

}
