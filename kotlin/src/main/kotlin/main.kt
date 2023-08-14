import org.json.JSONObject
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    /**
     * csv_providers ArrayList<id: number,
     *       email: string
     *       first_name: string
     *       last_name: string>
     */
    val users: ArrayList<Array<String>> = ArrayList()

    val csvUserReader = CsvUserReader("users.csv")

    users.addAll(csvUserReader.getUsers())
    users.addAll(getWebUsers())

    printUsers(users)
}

private fun printUsers(users: ArrayList<Array<String>>) {
    println("*********************************************************************************")
    println("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
    println("*********************************************************************************")
    for (user in users) {
        println(String.format("* %s\t* %s\t\t* %s\t\t* %s\t*", user[0], user[3], user[2], user[5]))
    }
    println("*********************************************************************************")
    println(users.size.toString() + " users in total!")
}

private fun getWebUsers(): ArrayList<Array<String>> {
    // Parse URL content
    val webUsers = ArrayList<Array<String>>()
    val url = "https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h";
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

