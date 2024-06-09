package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking {
    flow {
        repeat(3) {
            emit(doSomethingHttpRisky())
        }
    }.retryWhen { e: Throwable, attempts: Long ->
        println("Got $e, retrying")
        when {
            (e is Http5XX) && attempts > 10 -> false
            (e is Http4XX) && attempts > 3 -> false
            else -> true
        }
    }.collect { println(it) }
}

class Http5XX(message: String) : Throwable(message)

class Http4XX(message: String) : Throwable(message)

fun doSomethingHttpRisky(): Int {
    val randomNumber = Random.nextInt(10)
    if (randomNumber < 2) {
        throw Http5XX(randomNumber.toString())
    } else if (randomNumber < 5) {
        throw Http4XX(randomNumber.toString())
    }
    return randomNumber
}