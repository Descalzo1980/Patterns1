package algo

fun main() {
    val arr = arrayOf(
        1, 2, 3, 4, 5, 6, 7,8,9,8,9,9,9,9,9,9,9,9,9,10,
        1, 2, 3, 4, 5, 6, 7,8,9,8,9,9,9,9,9,9,9,9,9,10,
        1, 2, 3, 4, 5, 6, 7,8,9,8,9,9,9,9,9,9,9,9,9,10,
        1, 2, 3, 4, 5, 6, 7,8,9,8,9,9,9,9,9,9,9,9,9,10,
        1, 2, 3, 4, 5, 6, 7,8,9,8,9,9,9,9,9,9,9,9,9,10
    )
    val target = 10
    val (index, iterations) = search(arr, target)
    println("Target found at index: $index")
    println("Number of iterations: $iterations")
}

fun search(ints: Array<Int>, target: Int): Pair<Int, Int> {
    var left = 0
    var right = ints.size - 1
    var iterations = 0

    while (left <= right) {
        val mid = left + (right - left) / 2
        iterations++

        when {
            ints[mid] == target -> return Pair(mid, iterations)
            ints[mid] > target -> right = mid - 1
            else -> left = mid + 1
        }
    }
    return Pair(-1, iterations)
}