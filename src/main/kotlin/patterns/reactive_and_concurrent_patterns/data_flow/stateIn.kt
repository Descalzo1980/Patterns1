package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val originalFlow = flowOf(1, 2, 3, 4, 5)
        .onEach { println("Sending $it from ${Thread.currentThread().name}") }
    val stateFlow = originalFlow.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(),
        initialValue = 0
    )
    repeat(5) { id ->
        launch(Dispatchers.Default) {
            stateFlow.map { value ->
                println("Coroutine $id got $value on ${Thread.currentThread().name}")
            }.collect()
        }
        delay(100L)
    }
}