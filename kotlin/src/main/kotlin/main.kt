fun main(args: Array<String>) {
    val userReaders: List<UserReader> = listOf(
        CsvUserReader("users.csv", LocalFileReader()),
        WebUserReader("https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h")
    )
    val users = userReaders
        .flatMap { userReader -> userReader.getUsers() }

    UserPrinter().printUsers(users)
}



