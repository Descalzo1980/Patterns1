package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import kotlinx.coroutines.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val latch = CountDownLatch(10000)
    val c = AtomicInteger()


    val startCoroutine = System.currentTimeMillis()
    for (i in 1..10_000) {
        with(GlobalScope) {
            launch {
                c.incrementAndGet()
                delay(100)
                c.incrementAndGet()
                latch.countDown()
            }
        }
    }
        latch.await(10, TimeUnit.SECONDS)
    println("Executed ${c.get() / 2} coroutines in ${System.currentTimeMillis() - startCoroutine}ms")


    val startThread = System.currentTimeMillis()
    val threadCount = 100
    val tasksPerThread = latch.count / threadCount
    val threadLatch = CountDownLatch(10000)
    val threadCounter = AtomicInteger()

    repeat(threadCount) {
        thread {
            repeat(tasksPerThread.toInt()) {
                synchronized(threadCounter) {
                    threadCounter.incrementAndGet()
                }
                threadLatch.countDown()
            }
        }
    }
    threadLatch.await()
    println("Executed ${threadCounter.get()} threadEx in ${System.currentTimeMillis() - startThread}ms")
}