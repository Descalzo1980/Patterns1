package patterns.singleton

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