package algo

fun indSmallest(arr: MutableList<Int>): Int {
    var smallest = arr[0]
    var smallestIndex = 0

    for (i in 1..<arr.size) {
        if (arr[i] < smallest) {
            smallest = arr[i]
            smallestIndex = i
        }
    }
    return smallestIndex
}

fun selectionSort(arr: MutableList<Int>): List<Int> {
    val newArr = mutableListOf<Int>()
    while (arr.isNotEmpty()) {
        val smallestIndex = indSmallest(arr)
        newArr.add(arr.removeAt(smallestIndex))
    }
    return newArr
}

fun main(){
    val arr = mutableListOf(5, 3, 8, 1, 4)
    val sortedArr = selectionSort(arr)
    println(sortedArr)
}