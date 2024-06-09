package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val flow = flow {
        for (i in 1..10) {
            emit(i)
            delay(100)
        }
    }
    scope.launch {
        flow.collect { value ->
            println(value)
        }
    }
    scope.cancel()
}