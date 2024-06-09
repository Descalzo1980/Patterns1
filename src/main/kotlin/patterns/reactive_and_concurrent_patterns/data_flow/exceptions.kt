package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    runBlocking {
        flow {
            var i = 3
            repeat(5) {
                emit(10 / i--)
            }
        }
            .catch { e -> println("Caught exception: $e") }
            .collect { value -> println(value) }

        flow {
            emit(1)
            emit(2)
            emit(null)
        }
            .onCompletion { cause ->
                if (cause != null) {
                    println("Flow completed with exception: $cause")
                } else {
                    println("Flow completed successfully")
                }
            }
            .catch { e ->
                println("Caught exception: $e")
            }
            .collect {
                if (it == null) {
                    throw IllegalArgumentException("Null values are not allowed")
                }
                println(it)
            }
    }
}