package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

@OptIn(InternalCoroutinesApi::class)
suspend fun main() {
    val coroutine = runBlocking {
        async(coroutineContext.newCoroutineContext(Dispatchers.IO)) {
            withTimeout(500) {
                try {
                    val time = Random.nextLong(1000)
                    println("It will take me $time to do")
                    delay(time)
                    println("Returning profile")
                    "Profile"
                } catch (e: TimeoutCancellationException) {
                    e.printStackTrace()
                }
            }
        }
    }
    val result = try {
        coroutine.await()
    }catch (e: TimeoutCancellationException){
        e.printStackTrace()
    }
    println(result)
}
