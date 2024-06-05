package patterns.behavioral_patterns.chain_of_responsibility

import java.lang.IllegalArgumentException


fun main() {
    val request = Request(email = "junior.dev@example.com", question = "How to use Kotlin?")
    val request1 = Request(email = "developer@example.com", question = "How to use Kotlin?")
    handleRequest(request)
    handleRequest(request1)

    val req = Request("developer@example.com", "Why do we need Software Architects?")
    val chain = basicValidation(authentication(finalResponse()))
    val res = chain(req)
    println(res)
}

data class Response(val answer: String)

typealias Handler = (request: Request) -> Response


val authentication = fun(next: Handler) =
    fun(request: Request): Response {
        require(request.isKnownEmail()) { "Unknown email address" }
        return next(request)
    }


val basicValidation = fun(next: Handler) =
    fun(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next(request)
    }

val finalResponse = fun() =
    fun(request: Request): Response {
        return Response("I don't know")
    }


/*interface Handler {
    fun handle(request: Request): Response
}*/


/*class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        require(request.email.isNotEmpty()) { "Email must not be empty." }
        require(request.question.isNotEmpty()) { "Question must not be empty." }
        return next.handle(request)
    }
}*/

/******************************************************/


data class Request(val email: String, val question: String) {
    private val knownEmails = listOf("junior.dev@example.com", "developer@example.com")
    fun isKnownEmail(): Boolean {
        return email in knownEmails
    }

    fun isFromJuniorDeveloper(): Boolean {
        return email.contains("junior")
    }
}

fun handleRequest(r: Request) {
    if (r.email.isEmpty() || r.question.isEmpty()) {
        return
    }

    if (!r.isKnownEmail()) {
        return
    }
    if (!r.isFromJuniorDeveloper()) {
        return
    }
    println("I don't know. Did you check StackOverflow?")
}
