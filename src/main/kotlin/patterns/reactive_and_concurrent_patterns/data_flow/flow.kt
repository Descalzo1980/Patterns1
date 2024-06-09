package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    }*/
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

    }
}