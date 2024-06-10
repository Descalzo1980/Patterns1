package patterns.designing_for_concurrency.racing

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import kotlin.random.Random

fun main() = runBlocking {

    val deferred1 = async { delayAndReturn(1, "Task 1") }
    val deferred2 = async { delayAndReturn(2, "Task 2") }
    val deferred3 = async { delayAndReturn(3, "Task 3") }

    val result = awaitFirst(deferred1, deferred2, deferred3)

    println("First completed task: $result")


    val winner = select {
        preciseWeather().onReceive { preciseWeatherResult ->
            preciseWeatherResult
        }
        weatherToday().onReceive { weatherTodayResult ->
            weatherTodayResult
        }
    }
    println(winner)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.preciseWeather() = produce {
    delay(Random.nextLong(100))
    send("Precise Weather" to "+25c")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.weatherToday() = produce {
    delay(Random.nextLong(100))
    send("Weather Today" to "+24c")
}

suspend fun delayAndReturn(seconds: Long, result: String): String {
    delay(seconds * 1000)
    return result
}

suspend fun <T> awaitFirst(vararg deferreds: Deferred<T>): T {
    return coroutineScope {
        val result = CompletableDeferred<T>()
        deferreds.forEach { deferred ->
            launch {
                try {
                    result.complete(deferred.await())
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
        result.await()
    }
}