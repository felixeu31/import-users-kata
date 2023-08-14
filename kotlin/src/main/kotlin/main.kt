fun main(args: Array<String>) {
    val userReaders: List<UserReader> = listOf(
        CsvUserReader("users.csv"),
        WebUserReader("https://randomuser.me/api/?inc=gender,name,email,location&results=5&seed=a9b25cd955e2037h")
    )
    val users = userReaders
        .flatMap { userReader -> userReader.getUsers() }
        .map { rawUser ->
            User(
                rawUser[0].toLong(),
                rawUser[5],
                rawUser[2],
                rawUser[3],
            )
        }

    UserPrinter().printUsers(users)
}



