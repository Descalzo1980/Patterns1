package patterns.designing_for_concurrency.deadlocks

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex

fun main() = runBlocking {

    val mutexACorrect = Mutex()
    val mutexBCorrect = Mutex()
    val job1C = launch {
        println("Coroutine 1: Attempting to acquire Mutex A")
        mutexACorrect.lock()
        delay(1000) // Simulate some work
        println("Coroutine 1: Acquired Mutex A, now attempting to acquire Mutex B")
        mutexBCorrect.lock()
        println("Coroutine 1: Acquired Mutex B")
        mutexBCorrect.unlock()
        mutexACorrect.unlock()
    }
    val job2C = launch {
        // Corrected order
        println("Coroutine 2: Attempting to acquire Mutex A")
        mutexACorrect.lock()
        // Simulate some work
        delay(1000)
        println("Coroutine 2: Acquired Mutex A, now attempting to acquire Mutex B")
        mutexBCorrect.lock()
        println("Coroutine 2: Acquired Mutex B")
        mutexBCorrect.unlock()
        mutexACorrect.unlock()
    }
    job1C.join()
    job2C.join()

    /********************************************************/
    // Deadlock
    val mutexA = Mutex()
    val mutexB = Mutex()
    val job1 = launch {
        mutexA.lock()
        delay(1000) // Simulate some work
        println("Coroutine 1: Acquired Mutex A, now attempting to acquire Mutex B")
                mutexB.lock()
                println("Coroutine 1: Acquired Mutex B")
                mutexB.unlock()
                mutexA.unlock()
    }
    val job2 = launch {
        mutexB.lock()
        delay(1000) // Simulate some work
        println("Coroutine 2: Acquired Mutex B, now attempting to acquire Mutex A")
                mutexA.lock()
                println("Coroutine 2: Acquired Mutex A")
                mutexA.unlock()
                mutexB.unlock()
    }
    job1.join()
    job2.join()
}