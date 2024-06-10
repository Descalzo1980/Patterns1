package idioms_and_anti_patterns

import java.util.*

fun main(){

    val clintEastwoodQuotes = mapOf(
        "The Good, The Bad, The Ugly" to "Every gun makes its own tune.",
        "A Fistful Of Dollars" to "My mistake: four coffins."
    )

    val quote = clintEastwoodQuotes["A Fistful Of Dollars"]
    val quote1 = clintEastwoodQuotes.containsValue("My mistake: four coffins.")
    val quote2 = clintEastwoodQuotes.containsKey("A Fistful Of Dollars")

    quote?.let { println(it) }
    println(quote1)
    println(quote2)

    val bestSeanConneryMovie = JamesBondMovie().apply {
        movieName = "From Ukraine with Love"
    }

    println("${bestSeanConneryMovie.movieName}: ${bestSeanConneryMovie.actorName}")

    multiply(a = 2, b = 3)

    val evenSquares = (1..100).toList()
        .filter { it % 2 == 0 }
        .also { println(it) }
        .map { it * it }

    val lowerCaseName = JamesBondMovie().run {
        actorName = "ROGER MOORE"
        movieName = "THE MAN WITH THE GOLDEN GUN"
        actorName.lowercase(Locale.getDefault())
    }
    println(lowerCaseName)


    with(JamesBondMovie()) {
        actorName = "Pierce Brosnan"
        println(this.actorName)
    }
}


data class JamesBondMovie(
    var actorName: String = "Sean Connery",
    var movieName: String = "From Russia with Love"
)

fun multiply(a: Int, b: Int): Int = (a * b).also { println(it) }