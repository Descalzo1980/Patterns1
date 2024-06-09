package patterns.reactive_and_concurrent_patterns.data_flow

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main(){
    val numbers = 1..100
    var sum = 0
    for (n in numbers) {
        sum += n
    }

    val reduced: BigInteger = (1..100).map { it.toBigInteger() }.reduce { product, n -> product * n }
/*    println(reduced)*/

    val concatenated = listOf("Hello", "Kotlin", "Where", "my", "money" , "!").reduce { agg, s -> "$agg $s" }
    println(concatenated)

    val fold = (1..100).fold(0) {sum, n -> sum + n}
    println(fold)

    val scanned: List<Int> = (1..100).scan(0) { sum, n -> sum + n }
    println(scanned)

    val listOfLists: List<List<Int>> = listOf(listOf(1, 2), listOf(3, 4, 5), listOf(6, 7, 8))

    val flattened = mutableListOf<Int>()
    for (list in listOfLists) {
        flattened.addAll(list)
    }
    println("Flattened $flattened")

    val flattened1 = listOfLists.flatten()
    val flattened2 = listOfLists.flatMap { it }
    println("Flattened1 $flattened1")
    println("Flattened1 $flattened2")

    val seq: Sequence<Long> = generateSequence(1L) { it + 1 }
    println(seq.take(10).toList())

    val intList = (1..1_000_000).toList()
    println(measureTimeMillis {
        intList.map { it * it }.take(1).forEach { it }
    }) // 33

    println(measureTimeMillis {
        intList.asSequence().map { it * it }.take(1).forEach { it }
    }) // 2 это много быстрее в данном случае
}