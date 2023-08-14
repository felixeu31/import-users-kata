import org.json.JSONObject
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val users: ArrayList<Array<String>> = ArrayList()

    val csvUserReader = CsvUserReader("users.csv")
    val webUserReader = WebUserReader("https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h")

    users.addAll(csvUserReader.getUsers())
    users.addAll(webUserReader.getUsers())

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

