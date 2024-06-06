package patterns.reactive_and_concurrent_patterns.fp.immutability

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/*
ConcurrentModificationException
fun <T> printAndClear(list: MutableList<T>) {
    for (e in list) {
        println(e)
        list.remove(e)
    }
}*/
fun <T> printAndClear1(list: MutableList<T>) {
    for (e in list.toList()) {
        println(e)
        list.remove(e)
    }
}
fun <T> printAndClear2(list: MutableList<T>) {
    val iterator = list.iterator()
    while (iterator.hasNext()) {
        val e = iterator.next()
        println(e)
        iterator.remove()
    }
}

data class Player(var score: AtomicInteger)

fun generateMultiply(): (Int,Int) -> Int {
    return fun(x: Int, y: Int): Int {
        return x * y
    }
}

fun generateMultiply1(x: Int, y: Int): () -> Int {
    return fun(): Int {
        return x * y
    }
}

fun mathInvoker(x: Int, y: Int, mathFunction: (Int, Int) -> Int) {
    println(mathFunction(x, y))
}

fun main(){
/*    printAndClear(mutableListOf("a", "b", "c"))*/
/*    printAndClear1(mutableListOf("a", "b", "c"))
    printAndClear2(mutableListOf("a", "b", "c"))
    printAndClear2(mutableListOf(1, "b", "c"))

    val scores = listOf(Player(0),Player(0))
    val first = scores[0].score++
    scores[0].score = 2
    val second = scores[0].score
    println(first)
    println(second)*/

    val scores = listOf(Player(AtomicInteger(0)), Player(AtomicInteger(0)))
    val threads = List(2){
        thread {
            for (i in 1..1000){
                scores[0].score.incrementAndGet()
            }
        }
    }
    for (t in threads){
        t.join()
    }
    println(scores[0].score)

    val pair = "a" to 1
/*    pair.first = "b"
    pair.second = 2*/
    val(key, value) = pair
    println("$key => $value")
    for (p in mapOf(1 to "Sunday", 2 to "Monday")) {
        println("${p.key} ${p.value}")
    }

    val firstEdition = Triple("Design Patterns with Kotlin", 310, 2018)
    val secondEdition = Triple("Design Patterns with Kotlin, 2nd Edition",
        330, 2020)
    println(firstEdition.third)
    println(secondEdition.second)
    generateMultiply()(3,4)
    val multiplyFunction = generateMultiply()
    mathInvoker(5, 6, multiplyFunction)

    mathInvoker(7, 8) { x, y ->
        x * y
    }

    val dwarfs = listOf("Dwalin", "Balin", "Kili", "Fili", "Dori", "Nori",
        "Ori", "Oin", "Gloin", "Bifur", "Bofur", "Bombur", "Thorin")
    for (d in dwarfs) {
        println(d)
    }
    dwarfs.forEach(::println)
    val modifiedDwarfs = dwarfs.map {
        it.replaceFirstChar { char -> char.lowercaseChar() }
    }
    modifiedDwarfs.forEach {
        println(it)
    }
}





