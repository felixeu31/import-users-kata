import java.util.*

class CsvUserReader constructor(private val csvSource: String): UserReader {
    override fun getUsers(): List<User> {
        val classLoader = object {}.javaClass
        val stream = classLoader.getResourceAsStream(csvSource)
        val csvUsers: java.util.ArrayList<Array<String>> = java.util.ArrayList()
        val csvFile: Scanner = Scanner(stream)
        while (csvFile.hasNextLine()) {
            val line = csvFile.nextLine()
            // fields: ID, gender, Name ,country, postcode, email, Birthdate
            val attributes: Array<String> = line.split(",").toTypedArray()
            if (attributes.size == 0) {
                continue;
            }
            csvUsers.add(attributes)
        }
        csvUsers.removeAt(0) // Remove header column
        return csvUsers.map { rawUser ->
            User(
                rawUser[0].toLong(),
                rawUser[5],
                rawUser[2],
                rawUser[3],
            )
        }
    }

}
