package patterns.behavioral_patterns.template_method

fun main() {
    runSchedule(afterLunch = fun() {
        println("Discuss my lunch with boss' secretary")
        println("Read something not related to work")
    }, beforeLunch = {
        println("Look for my next trip destination")
        println("Read StackOverflow")
    }, bossHook = { println("Boss: Can we talk privately?") })


    val warrior = Warrior()
    warrior.completeTurn()
    val mage = Mage()
    mage.completeTurn()
    warrior.attack()
}

abstract class Character {
    fun completeTurn() {
        println("Finishing a turn with ${attack()} attack")
    }

    abstract fun attack(): String
}

class Mage : Character() {
    override fun attack() = "Fireball"
}

class Warrior: Character() {
    override fun attack() = "Sword"
}
/************************************************************/

fun runSchedule(
    beforeLunch: () -> Unit,
    afterLunch: () -> Unit,
    bossHook: (() -> Unit)? = fun() { println() }
) {
    fun arriveToWork() {
        println("How are you all?")
    }

    val drinkCoffee = { println("Did someone left the milk out?") }

    fun goToLunch() = println("I would like something italian")

    val goHome = fun() {
        println("Finally some rest")
    }

    arriveToWork()
    drinkCoffee()
    beforeLunch()
    goToLunch()
    afterLunch()
    bossHook?.let { it() }
    goHome()
}