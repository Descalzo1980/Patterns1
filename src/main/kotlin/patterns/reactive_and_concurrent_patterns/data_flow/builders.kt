package patterns.reactive_and_concurrent_patterns.data_flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun main() = runBlocking {

    val myFlow: Flow<Int> = flow {
        for (i in 1..3) {
            emit(i)
        }
    }

    val simpleFlow: Flow<Int> = flowOf(1, 2, 3)

    val array = arrayOf(1, 2, 3)
    val flowFromArray = array.asFlow()
    val list = listOf(4, 5, 6)
    val flowFromList = list.asFlow()

    val channelBasedFlow = channelFlow {
        send(1)
        send(2)
        send(3)
    }
}