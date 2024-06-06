package patterns.reactive_and_concurrent_patterns.fp.memoization


fun main(){
    val summarizer = summarizer()
    input.forEach {
        println(summarizer(it))
    }

    val stringLength: MutableMap<String, Int> = HashMap()
    stringLength["John"] = 5
}
fun sum(numbers: Set<Int>): Double {
    return numbers.sumOf { it.toDouble() }
}

fun summarizer(): (Set<Int>) -> Double {
    val resultsCache = mutableMapOf<Set<Int>, Double>()
    return { numbers: Set<Int> ->
        resultsCache.computeIfAbsent(numbers, ::sum)
    }
}
val input = listOf(
    setOf(1, 2, 3),
    setOf(3, 1, 2),
    setOf(2, 3, 1),
    setOf(4, 5, 6)
)