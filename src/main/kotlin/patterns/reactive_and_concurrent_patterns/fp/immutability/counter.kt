package patterns.reactive_and_concurrent_patterns.fp.immutability

fun counter(init: Int): () -> Int {
    var i = init
    return { i++ }
}


fun <T> withoutFirst(list: List<T>): T {
    return ArrayList(list).removeAt(0)
}

fun main(){
    val next = counter(10)
    println(next())
    println(next())
    println(next())

    val list = mutableListOf(1, 2, 3)
    println(withoutFirst(list))
    println(withoutFirst(list))
}