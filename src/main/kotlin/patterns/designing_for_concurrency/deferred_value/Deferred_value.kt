package patterns.designing_for_concurrency.deferred_value

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.random.Random

fun main() {
    runBlocking {
        val value = valueAsync()
        println(value.await())
    }

    getFilteredProducts().subscribeWith(object : DisposableSingleObserver<List<Product>>() {
        override fun onSuccess(result: List<Product>) {
            println(result)
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }
    })
    Thread.sleep(2000)
}

suspend fun valueAsync(): Deferred<String> = coroutineScope {
    val deferred = CompletableDeferred<String>()
    launch {
        delay(100)
        if (Random.nextBoolean()) {
            deferred.complete("OK")
        } else {
            deferred.completeExceptionally(
                RuntimeException()
            )
        }
    }
    deferred
}

data class Product(val id: Int, val name: String, val price: Double)

private val allProducts = listOf(
    Product(1, "Samsung", 1200.0),
    Product(2, "Oppo", 800.0),
    Product(3, "Nokia", 450.0),
    Product(4, "Lenovo", 550.0),
    Product(5, "ASUS", 400.0)
)

private fun getFilteredProducts(): Single<List<Product>> {
    return Single.just(
        allProducts
    ).map { products ->
        products.sortedBy { it.price }.filter { it.price > 500 }
    }.subscribeOn(Schedulers.io())
}