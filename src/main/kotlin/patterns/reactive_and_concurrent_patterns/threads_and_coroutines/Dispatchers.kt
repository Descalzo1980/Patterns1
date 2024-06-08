package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    runBlocking {
        launch {
            println(Thread.currentThread().name)
        }
        GlobalScope.launch {
            println("GlobalScope.launch: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println(Thread.currentThread().name)
        }
        async(Executors.newFixedThreadPool(4).asCoroutineDispatcher()){
            println(Thread.currentThread().name)
        }
    }
}