import java.util.*

class CsvUserReader constructor(private val csvSource: String, private val fileReader: FileReader): UserReader {
    override fun getUsers(): List<User> {
        val csvUsers: java.util.ArrayList<Array<String>> = java.util.ArrayList()

        val stream = fileReader.getFileAsInputStream(csvSource)

        val fileStreamScanner: Scanner = Scanner(stream)

        fileStreamScanner.nextLine()

        while (fileStreamScanner.hasNextLine()) {
            val line = fileStreamScanner.nextLine()
            // fields: ID, gender, Name ,country, postcode, email, Birthdate
            val attributes: Array<String> = line.split(",").toTypedArray()
            if (attributes.size == 0) {
                continue;
            }
            csvUsers.add(attributes)
        }

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
