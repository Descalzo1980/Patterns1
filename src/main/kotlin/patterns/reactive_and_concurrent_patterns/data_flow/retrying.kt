package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking {
    flow {
        repeat(3) {
            emit(doSomethingRisky())
        }
    }
        .retry(10)
        { e: Throwable ->
            println("Got $e, retrying")
            true
        }
        .collect { println(it) }
}


fun doSomethingRisky(): Int {
    val randomNumber = Random.nextInt(10)
    if (randomNumber > 4) {
        throw RuntimeException(randomNumber.toString())
    }
    return randomNumber
}