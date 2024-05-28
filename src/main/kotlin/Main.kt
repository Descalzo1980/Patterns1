import java.util.*
import kotlin.random.Random

data class First(
    val first: List<Int> = listOf()
)

data class Second(
    val second: List<String> = listOf()
)

fun main() {

    val first = First(listOf(1, 2, 3, 4, 5))
    val second = Second(listOf("1", "2", "3", "4", "5"))
    val myMap: Map<First, Second> = mapOf(first to second)

    val acc = myMap.map { entry -> entry.key.first.asReversed().zip(entry.value.second) }.flatten().toMap()
    val res = acc.entries.elementAtOrNull(100)?.key
    val res1 = acc.keys.firstOrNull()
    val res2 = first.first.component1()
    val pair = Triple(10, "hello", true)
    val (number, text, boolean) = pair
    println("Number: $number, Text: $text, Boolean: $boolean")
    println(acc)
    println(res)
    println(res1)
    println(res2)

    val editableHobbits = mutableListOf("Frodo", "Sam", "Pippin", "Merry")
    editableHobbits.add(0, "Bilbo")
    println(editableHobbits)
    val toTypedArray = editableHobbits.toTypedArray()
    val arrayAsString = editableHobbits.joinToString(separator = ", ")
    println(arrayAsString)
    val toString = editableHobbits.toString()
    val toHashSet = editableHobbits.toHashSet()

    /*    println(toTypedArray)*/
    println(toString)
    println(toHashSet)

    println(archenemy("Ololo"))

    val hero = "Batman"
    println("Archenemy of $hero is ${archenemy(hero)}")

    println(
        """Twinkle, Twinkle Little Bat
How I wonder what you're at!
Up above the world you fly,
Like a tea tray in the sky.
Twinkle, twinkle, little bat!
How I wonder what you're at!"""
    )

    println(
        """
 Twinkle, Twinkle Little Bat
 How I wonder what you're at!
 """.trimIndent()
    )

    println("""From "Alice's Adventures in Wonderland"""")

    for (c in "Word") {
        println(c)
    }

    val jokers = listOf("Heath Ledger", "Joaquin Phoenix", "Jack Nicholson")
    for (j in jokers) {
        println(j.trimIndent())
    }
    val res5 = jokers.joinToString(", ")
    println(res5)

    for (i in 'a'..'z') {
        println("This a letter $i")
    }

    for (i in 9 downTo 0) {
        println("for downTo $i")
    }

    val player = Player("Ololo")
    player.score = -10
    val scope = player.score
    println("Player name ${player.name} have scope $scope")
    val play = Play()
    println(play.rollDice())

    val activePlayer = ActivePlayer("Boo")
    activePlayer.rollDice()
    activePlayer.move(100,200)

    val user = User("Alexey", "abcd1234")
    println(user.password.hidePassword())
    println(user.username)
}


data class User(val username: String, val password: String){

}
fun String.hidePassword() = "*".repeat(this.length)

class ConfusedPlayer(name: String ):  Player(name) {

}

open class ActivePlayer(name: String): Moveable(), DiceRoller {

}

fun archenemy(heroName: String) = when (heroName) {
    "Batman" -> "Joker"
    "Superman" -> "Lex Luthor"
    "Spider-Man" -> "Green Goblin"
    else -> "Sorry, no idea"
}

open class Player(name: String) {
    val name = name
        get() = field.replaceFirstChar { it.uppercase() }
    var score: Int = 0
        set(value) {
            field = if(value >= 0){
                value
            }else{
                0
            }
        }
}

interface DiceRoller {
    fun rollDice(): Int = Random.nextInt(0,6)
}

class Play: DiceRoller

abstract class Moveable {
    private var x: Int = 0
    private var y: Int = 0
    open fun move(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}

