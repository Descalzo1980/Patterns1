package algo

fun main() {
    val arr = arrayOf(38, 27, 43, 3, 9, 82, 10)
    println("Original array: ${arr.joinToString()}")
    val sortedArr = mergeSort(arr)
    println("Sorted array: ${sortedArr.joinToString()}")
}

fun mergeSort(arr: Array<Int>): Array<Int> {
    if (arr.size <= 1) return arr

    val mid = arr.size / 2
    val left = arr.sliceArray(0..<mid)
    val right = arr.sliceArray(mid..<arr.size)

    return merge(mergeSort(left), mergeSort(right))
}

fun merge(left: Array<Int>, right: Array<Int>): Array<Int> {
    var leftIndex = 0
    var rightIndex = 0
    val merged = mutableListOf<Int>()

    while (leftIndex < left.size && rightIndex < right.size) {
        if (left[leftIndex] <= right[rightIndex]) {
            merged.add(left[leftIndex])
            leftIndex++
        } else {
            merged.add(right[rightIndex])
            rightIndex++
        }
    }

    while (leftIndex < left.size) {
        merged.add(left[leftIndex])
        leftIndex++
    }

    while (rightIndex < right.size) {
        merged.add(right[rightIndex])
        rightIndex++
    }

    return merged.toTypedArray()
}