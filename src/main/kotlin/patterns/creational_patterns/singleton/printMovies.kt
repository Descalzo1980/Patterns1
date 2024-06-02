package patterns.creational_patterns.singleton

object Logger {
    init {
        println("I was accessed for the first time")
        // Initialization logic goes here
    }
    // More code goes here
}


fun printMovies(movies: List<String>) {
    for(m in movies){
        println(m)
    }
}

fun main(){
    val myFavoriteMovies = listOf("Black Hawk Down", "Blade Runner")
    val myFavoriteMoviesEmpty: List<String> = listOf()
    val yourFavoriteMoviesEmpty: List<String> = listOf()
    printMovies(myFavoriteMoviesEmpty)
    val myObj = Singleton.getInstance()
    val myObjLazy = Singleton.INSTANCE

    val helloWorld = object {
        val hello = "Hello"
        val world = "World"

        override fun toString(): String = "$hello $world"
    }
    println(helloWorld)

    val instance = MyClass.create()
    val instanceAnon = MyClassAnon
    val instanceFactory = MyClassFactory
}

class Singleton private constructor() {

    companion object {
        // Volatile modifier is necessary
        @Volatile private var instance: Singleton? = null
        // synchronized to avoid concurrency problem
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Singleton().also { instance = it }
            }

        val INSTANCE: Singleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Singleton() }

    }
}

class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

class MyClassAnon {
    companion object {}
}

interface Factory<T> {
    fun create(): T
}

class MyClassFactory {
    companion object : Factory<MyClassFactory> {
        override fun create(): MyClassFactory = MyClassFactory()
    }
}