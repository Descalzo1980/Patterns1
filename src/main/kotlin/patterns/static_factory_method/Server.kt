package patterns.static_factory_method

class Server(port: Long) {

    init {
        println("Server started on port $port")
    }

    companion object Parser{
        fun withPost(port: Long) = Server(port)
    }
}

fun main(){
    val server = Server(8080)
    println(server)
    Server.Parser.withPost(8080)
/*
    val serverPrivate = ServerPrivate.withPostPrivate(3333)
*/
    val serverPrivate = ServerPrivate.withPostPrivate(3333)
}


class ServerPrivate private constructor(port: Long){
    init {
        println("Server started on port $port")
    }

    companion object ParserPrivate{
        fun withPostPrivate(port: Long) = ServerPrivate(port)
    }
}