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
    val monitor = Monitor(sensor)
    sensor.temp = 5
    sensor.detach(monitor)
    val newSensor = Sensor()
    val newMonitor = Monitor(newSensor)
    newSensor.temp = 10
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
