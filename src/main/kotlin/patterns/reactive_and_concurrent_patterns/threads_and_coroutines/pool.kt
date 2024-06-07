package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val pool = Executors.newFixedThreadPool(100)
    val counter = AtomicInteger(0)
    val start = System.currentTimeMillis()
    for (i in 1..10000) {
        pool.submit {
            counter.incrementAndGet()
            Thread.sleep(100)
            counter.incrementAndGet()
        }
    }
    println("Start time: $start")
    pool.awaitTermination(20, TimeUnit.SECONDS)
    pool.shutdown()
    println("Took me ${System.currentTimeMillis() - start} millis to complete ${counter.get() / 2} tasks")
}