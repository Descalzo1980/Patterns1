package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import kotlinx.coroutines.*
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    val job: Deferred<UUID> = fastUuidAsync()
    repeat(5) {
        delay(1000)
        when {
            job.isActive -> println("Я активная работа")
            job.isCompleted -> println("Я усталая работа")
            job.isCancelled -> println("Я отмененая работа")
        }
        println(job.await())
        val result = job.getCompleted()
        println(result)
        when {
            job.isActive -> println("Я активная работа")
            job.isCompleted -> println("Я усталая работа")
            job.isCancelled -> println("Я отмененая работа")
        }
    }
    delay(3000)
    job.cancel()
}

@OptIn(DelicateCoroutinesApi::class)
fun fastUuidAsync() = GlobalScope.async {
    UUID.randomUUID()
}