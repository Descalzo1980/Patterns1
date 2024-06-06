package patterns.behavioral_patterns.observer

fun main() {
    val catTheConductor = Cat()

    val bat = Bat()
    val dog = Dog()
    val turkey = Turkey()

    catTheConductor.joinChoir(bat::screech)
    catTheConductor.joinChoir(dog::howl)
    catTheConductor.joinChoir(dog::bark)
    catTheConductor.joinChoir(turkey::gobble)

    catTheConductor.conduct()
    catTheConductor.conduct()

    val sensor = Sensor()
    val sensor1 = Sensor1(10)
    val monitor = Monitor(sensor)
    val monitor1 = Monitor1(sensor1)
    sensor.temp = 5
    sensor1.temp = -5
}

interface Observer {
    fun update()
}

open class Subject {
    private var observers = mutableListOf<Observer>()

    fun callObserver() {
        for (obs in observers) obs.update()
    }

    fun attach(obs: Observer) {
        observers.add(obs)
    }

    fun detach(obs: Observer) {
        observers.remove(obs)
    }
}

class Sensor : Subject() {
    var temp: Int = 0
        set(value) {
            field = value
            callObserver()
        }
}

class Sensor1(init: Int) : Subject() {
    var temp: Int = init
        set(value) {
            field = value
            callObserver()
        }
}

class Monitor1(private val sensor: Sensor1) : Observer{
    init {
        sensor.attach(this)
    }

    override fun update() {
        val newTemp = sensor.temp
        println("Update Monitor, new temperature $newTemp ")
    }
}

class Monitor(private val sensor: Sensor) : Observer{
    init {
        sensor.attach(this)
    }

    override fun update() {
        val newTemp = sensor.temp
        println("Update Monitor, new temperature $newTemp ")
    }
}

/******************************************************************/


class Bat {
    fun screech() {
        println("Eeeeeee")
    }
}

class Turkey {
    fun gobble() {
        println("Gob-gob")
    }
}

class Dog {
    fun bark() {
        println("Woof")
    }

    fun howl() {
        println("Auuuu")
    }
}

class Cat {
    private val participants = mutableMapOf<() -> Unit, () -> Unit>()

    fun joinChoir(whatToCall: () -> Unit) {
        participants[whatToCall] = whatToCall
    }

    fun leaveChoir(whatNotToCall: () -> Unit) {
        participants.remove(whatNotToCall)
    }

    fun conduct() {
        for (p in participants.values) {
            p()
        }
    }
}

typealias Times = Int

enum class SoundPitch { HIGH, LOW }
interface Message {
    val repeat: Times
    val pitch: SoundPitch
}


data class LowMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.LOW
}

data class HighMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.HIGH
}
