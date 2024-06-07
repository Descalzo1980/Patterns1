package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.system.exitProcess

val counter = AtomicInteger()

fun main() {
    val thread: List<Thread> = try {
        List(10_000) {
            thread {
                Thread.sleep(1000)
                counter.incrementAndGet()
            }
        }
    }catch (oome: OutOfMemoryError){
        println("Spawned ${counter.get()} threads before crashing")
        exitProcess(-42)
    }
    thread.forEach {
        it.join()
    }
    println(
        "Finished without running Out of Memory consuming ${
            (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().
            freeMemory()) / 1024 / 1024
        }Mb"
    )

    println(
        "Free memory ${
            (Runtime.getRuntime().freeMemory() / 1024 / 1024)
        }Mb"
    )
}