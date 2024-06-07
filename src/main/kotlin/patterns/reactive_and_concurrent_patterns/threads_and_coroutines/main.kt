package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

fun main() {

/*    for (t in 0..1) {
        Thread {
            for (i in 0..2) {
                println("Thread $t: $i")
            }
        }.start()
    }
    repeat(2) { t ->
        thread {
            for (i in 1..2) {
                println("T$t: $i")
            }
        }
    }
    val t = thread(start = false) {
        for (i in 1..100){
            println("Thread like t: $i")
        }
    }
    t.start()

    thread(isDaemon = true) {
        for (i in 1..1_000_000) {
            println("daemon thread says: $i")
        }
    }
    Thread.sleep(10)*/

/*    var counter = 0 // raise condition
    val latch = CountDownLatch(100_000)
    repeat(100){
        thread {
            repeat(1000){
                counter++
                latch.countDown()
            }
        }
    }
    latch.await()
    println("Counter $counter")*/

    var counter = 0
    val latch = CountDownLatch(100_000)
    repeat(100){
        thread {
            repeat(1000){
                synchronized(latch){
                    counter++
                    latch.countDown()
                }
            }
        }
    }
    latch.await()
    println("Counter $counter")

}