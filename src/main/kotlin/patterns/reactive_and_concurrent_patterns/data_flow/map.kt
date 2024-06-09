package patterns.reactive_and_concurrent_patterns.data_flow

fun main(){
    val letters = 'a'..'z'
    val ascii = mutableListOf<Int>()
    for (i in letters){
        ascii.add(i.code)
    }
    println(ascii)
    val result: List<Int> = ('a'..'z').map { it.code }
    println(result)

    val numbers = 1..100
    val notFuzzBuzz = mutableListOf<Int>()
    for (i in numbers){
        if(i % 3 == 0 || i % 5 == 0){
            notFuzzBuzz.add(i)
        }
    }
    println(notFuzzBuzz)

    val filtered: List<Int> = numbers.filter { it % 3 == 0 || it % 5 == 0 }
    println(filtered)

    findFizzbuzz(numbers.toList())

    val found = (1..100).find { it % 3 == 0 || it % 5 == 0 }
    println(found)

    val res = numbers
        .asSequence()
        .map { it * it }
        .filter { it < 20 }
        .onEach { println(it) }
        .sortedDescending()
        .filter { it > 5 }
        .toList()
    println(res)

    numbers.map { it * it }
        .forEachIndexed { index, value ->
            print("$index:$value, ")
        }
}

fun findFizzbuzz(numbers: List<Int>): Int? {
    for (n in numbers) {
        if (n % 3 == 0 && n % 5 == 0) {
            return n
        }
    }
    return null
}