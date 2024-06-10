package patterns.example.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Starting...")

    launch {
        logTask("Task 1")
    }

    launch {
        logTask("Task 2")
    }

    println("Finished...")
}

suspend fun logTask(name: String) {
    repeat(5) { i ->
        delay(500L) // Имитация работы
        println("$name is running... iteration $i")
    }
}

/*Конкуренция (Concurrency)
Конкуренция означает выполнение нескольких
 задач практически одновременно
  (в одном потоке), с переключением между задачами.*/