package patterns.behavioral_patterns.state

fun main() {
    val snail = Snail()
    snail.seeHero()
    snail.getHit(1)
    snail.getHit(10)

    val light = TrafficLight() // It start as green
    light.carAction() // GREEN: Cars are driving
    light.carAction() // YELLOW: Cars are starting to brake
    light.carAction() // RED: Cars are waiting
    light.carAction() // GREEN: Cars are driving
}

sealed interface State {
    fun carAction()
    class GreenLightState(private val trafficLight: TrafficLight) : State {
        override fun carAction() {
            println("GREEN: Cars are driving")
            trafficLight.changeState(YellowLightState(trafficLight))
        }
    }

    class YellowLightState(private val trafficLight: TrafficLight) : State {
        override fun carAction() {
            println("YELLOW: Cars are starting to brake")
            trafficLight.changeState(RedLightState(trafficLight))
        }
    }

    class RedLightState(private val trafficLight: TrafficLight) : State {
        override fun carAction() {
            println("RED: Cars are waiting")
            trafficLight.changeState(GreenLightState(trafficLight))
        }
    }
}

class TrafficLight {
    private var state: State = State.GreenLightState(this)
    fun changeState(state: State) {
        this.state = state
    }

    fun carAction() {
        state.carAction()
    }
}


/*******************************************************************/
interface WhatCanHappen {
    fun seeHero()

    fun getHit(pointsOfDamage: Int)

    fun calmAgain()
}

class Snail : WhatCanHappen {
    private var healthPoints = 10
    private var mood: Mood = Still

    override fun seeHero() {
        mood = when (mood) {
            is Still -> {
                println("Aggressive")
                Aggressive
            }
            else -> {
                println("No change")
                mood
            }
        }
    }

    override fun getHit(pointsOfDamage: Int) {
        println("Hit for $pointsOfDamage points")
        healthPoints -= pointsOfDamage

        println("Health: $healthPoints")
        mood = when {
            (healthPoints <= 0) -> {
                println("Dead")
                Dead
            }
            mood is Aggressive -> {
                println("Retreating")
                Retreating
            }
            else -> {
                println("No change")
                mood
            }
        }
    }

    override fun calmAgain() {
    }
}

/*
sealed class Mood {
    // Some abstract methods here, like draw(), for example
}

data object Still : Mood()

data object Aggressive : Mood()

data object Retreating : Mood()

data object Dead : Mood()*/

sealed interface Mood {
    // Some abstract methods here, like draw(), for example
}

data object Still : Mood {

}

data object Aggressive : Mood

data object Retreating : Mood

data object Dead : Mood