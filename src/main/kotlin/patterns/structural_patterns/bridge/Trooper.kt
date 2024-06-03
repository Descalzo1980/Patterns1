package patterns.structural_patterns.bridge


// if we add shout() add error's fun shout(): String? so this a bad way
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
}

open class StormTrooper: Trooper {
    override fun move(x: Long, y: Long) {
        // Move at normal speed
    }

    override fun attackRebel(x: Long, y: Long) {
        // Missed most of the time
    }

}

open class ShockTrooper : Trooper {
    override fun move(x: Long, y: Long) {
        // Moves slower than regular StormTrooper
    }
    override fun attackRebel(x: Long, y: Long) {
        // Sometimes hits
    }
}

class RiotControlTrooper : StormTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        // Has an electric baton, stay away!
    }
}
class FlameTrooper : ShockTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        // Uses flametrower, dangerous!
    }
}

class ScoutTrooper : ShockTrooper() {
    override fun move(x: Long, y: Long) {
        // Runs faster
    }
}

