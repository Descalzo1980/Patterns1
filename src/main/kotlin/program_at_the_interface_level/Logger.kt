package program_at_the_interface_level

interface Logger {
    fun log(message: String)
}
// Реализация интерфейса Logger
class FileLogger(private val fileName: String) : Logger {
    override fun log(message: String) {
        println("Writing to file $fileName: $message")
    }
}
// Реализация интерфейса Logger
class ConsoleLogger : Logger {
    override fun log(message: String) {
        println("Console log: $message")
    }
}

fun main() {

    // Используем абстракцию (интерфейс)
    val logger: Logger = FileLogger("app.log")
    val consoleLogger: Logger = ConsoleLogger()

    // Код не зависит от конкретной реализации
    logger.log("FileLogger")
    consoleLogger.log("ConsoleLogger")
}
