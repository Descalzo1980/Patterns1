package patterns.example.parallelism

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Starting...")

    val job1 = launch(Dispatchers.Default) {
        logTask("Task 1")
    }

    val job2 = launch(Dispatchers.Default) {
        logTask("Task 2")
    }

    joinAll(job1, job2)

    println("Finished...")
}

suspend fun logTask(name: String) {
    repeat(5) { i ->
        delay(500L) // Имитация работы
        println("$name is running... iteration $i")
    }
}

/*Параллелизм (Parallelism)
Параллелизм означает выполнение нескольких задач одновременно
на разных ядрах процессора. Для этого мы можем использовать Dispatchers.Default,
 который создает пул потоков для выполнения задач параллельно.*/