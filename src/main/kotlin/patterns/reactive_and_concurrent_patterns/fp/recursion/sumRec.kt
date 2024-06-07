package patterns.reactive_and_concurrent_patterns.fp.recursion

fun main(){
    val numbers = List(1_000_000) {it}
/*    val num = (0..< 1000000).toList()*/
    println(sumRec(0,0,numbers))

    val sum = calculate(3,4) { a, b -> a + b }
    println(sum)
}

// StackOverflowError
/*
fun sumRec(i: Int, sum: Long, numbers: List<Int>): Long {
    return if (i == numbers.size) {
        return sum
    } else {
        sumRec(i+1, numbers[i] + sum, numbers)
    }
}*/
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}
tailrec fun sumRec(i: Int, sum: Long, numbers: List<Int>): Long {
    return if (i == numbers.size) {
        return sum
    } else {
        sumRec(i+1, numbers[i] + sum, numbers)
    }
}

tailrec fun mergeSort(numbers: List<Int>): List<Int> {
    return when {
        numbers.size <= 1 -> numbers
        numbers.size == 2 -> {
            return if (numbers[0] < numbers[1]) {
                numbers
            } else {
                listOf(numbers[1], numbers[0])
            }
        }
        else -> {
            val left = mergeSort(numbers.slice(0..numbers.size / 2))
            val right = mergeSort(
                numbers.slice((numbers.size / 2 + 1) ..< numbers.size))
            return merge(left, right)
        }
    }
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    var i = 0
    var j = 0
    val mergedList = mutableListOf<Int>()

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            mergedList.add(left[i])
            i++
        } else {
            mergedList.add(right[j])
            j++
        }
    }

    while (i < left.size) {
        mergedList.add(left[i])
        i++
    }

    while (j < right.size) {
        mergedList.add(right[j])
        j++
    }

    return mergedList
}