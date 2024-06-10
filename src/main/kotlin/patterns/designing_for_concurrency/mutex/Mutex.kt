package patterns.designing_for_concurrency.mutex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
/*    mutexWithLock()

    noMutex()*/
    val str = "ololo".toBig()
    println(str)
}

fun mutexWithLock() {
    runBlocking {
        var counter = 0
        val mutex = Mutex()
        val jobs = List(10) {
            async(Dispatchers.Default) {
                repeat(1000) {
                    mutex.withLock {
                        counter++
                    }
                }
            }
        }
        jobs.awaitAll()

        println(counter)
    }
}

fun noMutex(){
    runBlocking {
        var counter = 0
        val jobs = List(10) {
            async(Dispatchers.Default) {
                repeat(1000) {
                        counter++
                }
            }
        }
        jobs.awaitAll()

        println(counter)
    }
}

fun String.toBig(): String {
    return this.uppercase()
}