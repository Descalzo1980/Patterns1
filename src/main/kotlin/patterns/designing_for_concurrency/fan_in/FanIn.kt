package patterns.designing_for_concurrency.fan_in

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>()

    // Fan-Out
    val workers = List(5) { index ->
        launch(Dispatchers.Default) {
            repeat(10) {
                val result = doWork(index * 10 + it)
                channel.send(result)
            }
        }
    }

    // Fan-In
    val collector = launch(Dispatchers.Default) {
        channel.consumeEach { result ->
            println("Received: $result")
        }
    }

    workers.forEach { it.join() }
    channel.close()
    collector.join()
}

suspend fun doWork(id: Int): Int {
    delay((500L..1500L).random())
    return id * id
}