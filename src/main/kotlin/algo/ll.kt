package algo

class CustomLinkedList<T> {
    private class Node<T>(var data: T, var next: Node<T>? = null)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun add(element: T) {
        val newNode = Node(element)
        if (tail == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    fun get(index: Int): T {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        var current = head
        for (i in 0..<index) {
            current = current?.next
        }
        return current?.data ?: throw IllegalStateException("Unexpected null value")
    }

    fun remove(index: Int): T {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        return if (index == 0) {
            val removedElement = head?.data ?: throw IllegalStateException("Unexpected null value")
            head = head?.next
            if (head == null) {
                tail = null
            }
            size--
            removedElement
        } else {
            var current = head
            for (i in 0..<index - 1) {
                current = current?.next
            }
            val removedElement = current?.next?.data ?: throw IllegalStateException("Unexpected null value")
            current.next = current.next?.next
            if (current.next == null) {
                tail = current
            }
            size--
            removedElement
        }
    }

    fun size(): Int {
        return size
    }

    override fun toString(): String {
        val result = StringBuilder("[")
        var current = head
        while (current != null) {
            result.append(current.data)
            if (current.next != null) {
                result.append(", ")
            }
            current = current.next
        }
        result.append("]")
        return result.toString()
    }
}

fun main() {
    val linkedList = CustomLinkedList<String>()
    linkedList.add("Dog")
    linkedList.add("Cat")
    linkedList.add("Mouse")
    println(linkedList) // Output: [Dog, Cat, Mouse]
    println(linkedList.get(1)) // Output: Cat
    linkedList.remove(1)
    println(linkedList) // Output: [Dog, Mouse]
}