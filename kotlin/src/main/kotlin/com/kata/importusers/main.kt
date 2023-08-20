import com.kata.importusers.infra.ConsolePrinter
import com.kata.importusers.infra.LocalFileReader
import com.kata.importusers.infra.PublicApiClient
import com.kata.importusers.infra.UserPrinter
import com.kata.importusers.readers.UserReader
import com.kata.importusers.readers.CsvUserReader
import com.kata.importusers.readers.WebUserReader

fun main(args: Array<String>) {
    val userReaders: List<UserReader> = listOf(
        CsvUserReader("users.csv", LocalFileReader()),
        WebUserReader(
            "https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h",
            PublicApiClient()
        )
    )
    val users = userReaders
        .flatMap { userReader -> userReader.getUsers() }

    UserPrinter(ConsolePrinter()).printUsers(users)
}



