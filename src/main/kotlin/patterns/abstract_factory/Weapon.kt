package patterns.abstract_factory

interface Weapon {

    fun use():String
}

abstract class WeaponFactory {

    abstract fun buildWeapon(): Weapon
}

class Crossbow : Weapon {

    companion object Factory : WeaponFactory() {
        override fun buildWeapon() = Crossbow()
    }

    override fun use(): String {
        return "Using crossbow weapon"
    }
}

class Katana: Weapon {

    companion object Factory: WeaponFactory(){
        override fun buildWeapon(): Weapon {
            return Katana()
        }

    }

    override fun use(): String {
        return "Using katana weapon"
    }
}

fun main(){
    val factory: WeaponFactory = Crossbow.Factory
    val factory1: WeaponFactory = Katana.Factory
    val crossbow = factory.buildWeapon()
    val katana = factory1.buildWeapon()
    println(crossbow.use())
    println(katana.use())
}