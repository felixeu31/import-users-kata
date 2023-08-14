class UserPrinter {
    fun printUsers(users: List<Array<String>>) {
        println("*********************************************************************************")
        println("* ID\t\t\t* COUNTRY\t\t* NAME\t\t\t\t* EMAIL\t\t\t\t\t\t*")
        println("*********************************************************************************")
        for (user in users) {
            println(String.format("* %s\t* %s\t\t* %s\t\t* %s\t*", user[0], user[3], user[2], user[5]))
        }
        println("*********************************************************************************")
        println(users.size.toString() + " users in total!")
    }
}
