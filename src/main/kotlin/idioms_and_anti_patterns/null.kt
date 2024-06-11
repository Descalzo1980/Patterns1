package idioms_and_anti_patterns

import kotlin.random.Random

fun main() {

    val stringOrNull: String? = if (Random.nextBoolean()) "String" else null
    if (stringOrNull != null) {
        println(stringOrNull.length)
    }

    val alwaysLength = stringOrNull?.length ?: 0
    println(alwaysLength)

    val response: Response? = Response(UserProfile(null, null))
    println(response?.profile?.firstName?.length)

    println(response?.let { response ->
        response.profile?.let { userProfile ->
            userProfile.firstName?.length
        }
    })

    println(response?.run {
        profile?.run {
            firstName?.length
        }
    })

    println(response?.runCatching {
        profile?.runCatching {
            firstName?.length
        }
    })

    val user = User1(
        mapOf(
            "name" to "John Doe",
            "age" to 25
        )
    )
    println(user.age)
    println(user.name)
    println(user.map)
}

data class Response(val profile: UserProfile?)
data class UserProfile(val firstName: String?, val lastName: String?)

class User1(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}