package patterns.behavioral_patterns.iterator

open class Trooper() {
    fun move(x: Int, y: Int) {
        println("Moving to $x:$y")
    }
}

/*class Squad(private val units: List<Trooper>, name: String = "Unknown") : Trooper(name), Iterable<Trooper> {
    constructor(vararg units: Trooper) : this(units.toList())

    override fun iterator(): Iterator<Trooper> {
        return units.iterator()
    }

    override fun toString(): String {
        return "Squad: ${units.size} troopers"
    }
}*/

class Squad(private val units: List<Trooper>) : Trooper() {

    constructor(vararg units: Trooper) : this(units.toList())

    operator fun iterator(): Iterator<Trooper> {
        return TrooperIterator(units)
    }
}

class TrooperIterator(private val units: List<Trooper>) : Iterator<Trooper> {
    private var i = 0
    private var iterator: Iterator<Trooper> = this
    override fun hasNext(): Boolean {
        if (i >= units.size) {
            return false
        }
        if (i == units.size - 1) {
            if (iterator != this) {
                return iterator.hasNext()
            }
        }
        return true
    }

    override fun next(): Trooper {
        if (iterator != this) {
            if (iterator.hasNext()) {
                return iterator.next()
            } else {
                i++
                iterator = this
            }
        }

        return when (val e = units[i]) {
            is Squad -> {
                iterator = e.iterator()
                this.next()
            }
            else -> {
                i++
                e
            }
        }
    }
}

fun main() {
    val platoon = Squad(
        Trooper(),
        Squad(
            Trooper(),
        ),
        Trooper(),
        Squad(
            Trooper(),
            Trooper(),
        ),
        Trooper()
    )

    val l = listOf<String>()

    l.iterator()

    for (trooper in platoon) {
        println(trooper)
    }
    val myIterator = mutableListOf("1", 2, null, true).iterator()
    printAnything(platoon.iterator())
    while (myIterator.hasNext()) {
        println(myIterator.next())
    }
}

fun printAnything(iterator: Iterator<Trooper>) {
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}
