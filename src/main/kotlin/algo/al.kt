package algo

class CustomArrayList<T> {
    private var array: Array<Any?> = arrayOfNulls<Any>(10)
    private var size = 0

    fun add(element: T) {
        if (size == array.size) {
            array = array.copyOf(array.size * 2)
        }
        array[size] = element
        size++
    }

    fun get(index: Int): T {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        return array[index] as T
    }

    fun remove(index: Int): T {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        val removedElement = array[index]
        for (i in index..<size - 1) {
            array[i] = array[i + 1]
        }
        array[size - 1] = null
        size--
        return removedElement as T
    }

    fun size(): Int {
        return size
    }

    override fun toString(): String {
        return array.take(size).toString()
    }
}

fun main() {
    val arrayList = CustomArrayList<String>()
    arrayList.add("Apple")
    arrayList.add("Banana")
    arrayList.add("Cherry")
    println(arrayList) // Output: [Apple, Banana, Cherry]
    println(arrayList.get(1)) // Output: Banana
    arrayList.remove(1)
    println(arrayList) // Output: [Apple, Cherry]
}