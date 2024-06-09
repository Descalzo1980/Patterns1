package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ObsoleteCoroutinesApi::class)
fun main(){
    runBlocking {
        val actor = actor<Int> {
            channel.consumeEach {
                println(it)
            }
        }
        (1..10).forEach {
            actor.send(it)
        }

        val actor1 = actor<Long> {
            var prev = 0L
            channel.consumeEach {
                println(it - prev)
                prev = it
                delay(100)
            }

        }
        repeat(10) {
            actor1.send(System.currentTimeMillis())
        }
        actor1.close().also { println("Done sending") }


        val channel = Channel<Int>()

        launch {
            for (i in 1..5) {
                channel.send(i)
            }
            channel.close()
        }

        launch {
            for (received in channel) {
                println("Received: $received")
            }
        }
    }
}