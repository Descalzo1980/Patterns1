package algo

fun main(){
    val arr = arrayOf(1..1000000)

    val timeStart = System.currentTimeMillis()

    val result = bigOOne(arr)
    val timeFinish = System.currentTimeMillis()
    println("Result: $result")
    println("Execution time: ${timeFinish - timeStart} ms")
}


fun bigOOne(arr: Array<IntRange>): IntRange {
    return arr[arr.size -1]
}

//O(1)