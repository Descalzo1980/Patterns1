package idioms_and_anti_patterns

fun main() {
    val c = User("Stas")

    println(c.resetPassword)

    val noArgClass = User::class.java.getConstructor().newInstance()
    println(noArgClass.resetPassword)
}

// Don't do this
/*
class User(val name: String, val resetPassword: Boolean) {
    constructor(name: String) : this(name, true)
} */

@NeedsNoArgs
class User(val name: String, val resetPassword: Boolean = true)


annotation class NeedsNoArgs