package patterns.structural_patterns.bridge


typealias PointsOfDamage = Long
typealias Meters = Int

const val RIFLE_DAMAGE: PointsOfDamage = 3L
const val REGULAR_SPEED: Meters = 1

interface TrooperNew {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
    fun retreat() {
        println("Retreating!")
    }
}

interface Legs {
    fun move(x: Long, y: Long): Meters
}

interface Weapon {
    fun attack(x: Long, y: Long): PointsOfDamage
}

data class StormTrooperNew(
    private val weapon: Weapon,
    private val legs: Legs,
): TrooperNew {
    override fun move(x: Long, y: Long) {
        legs.move(x, y)
    }
    override fun attackRebel(x: Long, y: Long) {
        weapon.attack(x, y)
    }

    override fun toString(): String {
        return "StormTrooperNew(" +
                "weapon=${weapon::class.simpleName}," +
                " legs=${legs::class.simpleName},"
    }
}

class Rifle : Weapon {
    override fun attack(x: Long, y: Long) = RIFLE_DAMAGE
}
class Flamethrower : Weapon {
    override fun attack(x: Long, y: Long)= RIFLE_DAMAGE * 2
}
class Batton : Weapon {
    override fun attack(x: Long, y: Long)= RIFLE_DAMAGE * 3
}

class RegularLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED
}
class AthleticLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED * 2
}

fun main() {
    val stormTrooper = StormTrooperNew(Rifle(), RegularLegs())
    val flameTrooper = StormTrooperNew(Flamethrower(), RegularLegs())
    val scoutTrooper = StormTrooperNew(Batton(), AthleticLegs())
    println(listOf(stormTrooper, flameTrooper, scoutTrooper))


    val woodenTable: Furniture = Table(material = Wood())
    val metalTable: Furniture = Table(material = Metal())
    woodenTable.build() // Building Table from wood
    metalTable.build() // Building Table from metal

    val woodenChair: Furniture = Chair(material = Wood())
    val metalChair: Furniture = Chair(material = Metal())
    woodenChair.build() // Building Chair from wood
    metalChair.build() // Building Chair from metal
}


interface Material {
    fun collect(): String
}

class Wood : Material {
    override fun collect() = "wood"
}

class Metal : Material {
    override fun collect() = "metal"
}

interface Furniture {
    fun build()
}

data class Chair(
    private val material: Material,
) : Furniture {
    override fun build() {
        println("Building Chair from " + material.collect())
    }
}

data class Table(
    private val material: Material,
) : Furniture {
    override fun build() {
        println("Building Table from " + material.collect())
    }
}