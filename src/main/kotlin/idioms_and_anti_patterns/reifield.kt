package idioms_and_anti_patterns

import kotlin.reflect.KClass

fun main() {
    printIfSameType(Int::class, 1) // Print 1, as 1 is Int
    printIfSameType(Int::class, 2L) // Prints no, as 2 is Long
    printIfSameType(Long::class, 3L) // Prints yes, as 3 is Long

    printIfSameReified<Int>(1) // Print 1, as 1 is Int
    printIfSameReified<Int>(2L) // Prints no, as 2 is Long
    printIfSameReified<Long>(3L) // Prints yes, as 3 is Long

    val p: Pair<String, String> = "a" to "b"

    val intList = listOf(1, 2, 3, 4, 5)
    val longList = listOf(1L, 2L, 3L, 4L, 5L)
    val stringList = listOf("one", "two", "three", "four", "five")
    printList(intList)
    printList(longList)
    printList(stringList)
}

fun <T : Number> printIfSameType(clazz: KClass<T>, a: Number) {
    if (clazz.isInstance(a)) {
        println("Yes")
    } else {
        println("No")
    }
}

inline fun <reified T : Number> printIfSameReified(a: Number) {
    if (a is T) {
        println("Yes")
    } else {
        println("No")
    }
}

inline fun <reified T : Any> printList(list: List<T>) {
    when {
        1 is T -> println("This is a list of Ints")
        1L is T -> println("This is a list of Longs")
        else -> println("This is a list of something else")
    }
    println(list)
}