package algo

fun main(){
/*    val arr = LongArray(1000000000) { it + 1L }*/
    val n = 1_000_000_000L
    val timeStart = System.currentTimeMillis()
/*    println(bigON(arr))*/
    println(bigON(n))
    val timeFinish = System.currentTimeMillis()
    println("Execution time: ${timeFinish - timeStart} ms")
}


fun bigON(n: Long): Long {
    var sum = 0L
    for (i in 1..n) {
        sum += i
    }
    return sum
}

//O(n)