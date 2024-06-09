package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
/*    val numFlow: Flow<Int> = flow {
        (0..10).forEach {
            println("Sending $it")
            emit(it)
        }
    }

    numFlow.collect { value ->
        println("Received $value")
    }
    runBlocking {
        val numbersFlow: Flow<Int> = flow {
            println("New subscriber!")
            (1..10).forEach {
                println("Sending $it")
                emit(it)
                if (it == 9) {
                    throw RuntimeException()
                }
            }
        }
        try {
            numbersFlow.collect { number ->
                println("Listener received $number")
            }
        }catch (e: Exception){
            println("Got an error")
        }
    }*/

    runBlocking {
        val numbersFlow: Flow<Int> = flow {
            println("New subscriber!")
            (1..10).forEach {
                println("Sending $it")
                emit(it)
            }
        }
        (1..4).forEach { coroutineId ->
            delay(5000)
            numbersFlow.buffer().collect { number ->
                delay(1000)
                println("Coroutine $coroutineId received $number")
            }
        }
    }
}