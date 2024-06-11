package idioms_and_anti_patterns


fun main(){
    doCoolStuff(Batman)
    doCoolStuff(Superman)
}

sealed interface Superhero

data object Batman : Superhero {
    fun callRobin() {
        println("To the Bat-pole, Robin!")
    }
}

data object Superman : Superhero {
    fun fly() {
        println("Up, up and away!")
    }
}
// Function invoking a superhero's power
/*fun doCoolStuff(s: Superhero) {
    if (s is Superman) {
        s.fly()
    } else if (s is Batman) {
        s.callRobin()
    }
}*/

fun doCoolStuff(s: Superhero) {
    when(s) {
        is Superman -> s.fly()
        is Batman -> s.callRobin()
    }
}