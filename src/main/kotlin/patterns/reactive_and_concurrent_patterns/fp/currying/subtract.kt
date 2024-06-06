package patterns.reactive_and_concurrent_patterns.fp.currying

fun subtract(x: Int, y: Int): Int{
    return x - y
}

fun subtract(x: Int) = fun(y: Int): Int {
    return x - y
}

enum class LogLevel {
    ERROR, WARNING, INFO
}
fun log(level: LogLevel, message: String) = println("$level: $message")

fun createLogger(level: LogLevel): (String) -> Unit {
    return { message: String ->
        log(level, message)
    }
}

fun main() {
    val errorLog = fun(message: String) {
        log(LogLevel.ERROR, message)
    }

    errorLog("This is an error message.")

    val logMessage = fun(level: LogLevel, message: String) {
        when(level) {
            LogLevel.ERROR -> log(LogLevel.ERROR, message)
            LogLevel.WARNING -> log(LogLevel.WARNING, message)
            LogLevel.INFO -> log(LogLevel.INFO, message)
        }
    }

    logMessage(LogLevel.WARNING, "This is a warning message.")
    logMessage(LogLevel.INFO, "This is an info message.")
    logMessage(LogLevel.ERROR , "This is an error message.")

    val infoLogger = createLogger(LogLevel.INFO)
    infoLogger("Log something")
}