package algo

import kotlin.coroutines.*

fun main() {
    val continuation = suspend {
        println("Coroutine started")
        suspendCoroutine<Unit> { cont ->
            println("Coroutine suspended")
            cont.resume(Unit)
        }
        println("Coroutine resumed")
    }.createCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<Unit>) {
            println("Coroutine completed with $result")
        }
    })

    continuation.resume(Unit)
}