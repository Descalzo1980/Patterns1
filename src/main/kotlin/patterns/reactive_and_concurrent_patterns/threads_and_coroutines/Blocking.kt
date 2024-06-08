package patterns.reactive_and_concurrent_patterns.threads_and_coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis


suspend fun main() {
    val t1 = measureTimeMillis {
        Blocking.profile("123")
    }

    val t2 = measureTimeMillis {
        Async().profile("123")
    }

    val t3 = measureTimeMillis {
        Suspend().profile("123")
    }

    println("Blocking code: $t1")
    println("Async: $t2")
    println("Suspend: $t3")
}

data class Profile(val bio: String, val picture: ByteArray?, val friends: List<String>)

class Suspend {
    suspend fun profile(id: String): Profile {
        val bio = fetchBioOverHttp(id) // takes 1s
        val picture = fetchPictureFromDB(id) // takes 100ms
        val friends = fetchFriendsFromDB(id) // takes 500ms
        return Profile(bio, picture, friends)
    }

    private suspend fun fetchFriendsFromDB(id: String): List<String> {
        delay(500)
        return emptyList()
    }

    private suspend fun fetchPictureFromDB(id: String): ByteArray? {
        delay(100)
        return null
    }

    private suspend fun fetchBioOverHttp(id: String): String {
        delay(1000)
        return "Just my"
    }
}

class Blocking {
    companion object {
        fun profile(id: String): Profile {
            val bio = fetchBioOverHttp(id) // takes 1s
            val picture = fetchPictureFromDB(id) // takes 100ms
            val friends = fetchFriendsFromDB(id) // takes 500ms
            return Profile(bio, picture, friends)
        }
        private fun fetchFriendsFromDB(id: String): List<String> {
            Thread.sleep(500)
            return emptyList()
        }
        private fun fetchPictureFromDB(id: String): ByteArray? {
            Thread.sleep(100)
            return null
        }
        private fun fetchBioOverHttp(id: String): String {
            Thread.sleep(1000)
            return "Just my"
        }
    }
}

class Async {
    private val scope = CoroutineScope(Dispatchers.Default)
    suspend fun profile(id: String): Profile {
        val bio = fetchBioOverHttpAsync(id) // takes 1s
        val picture = fetchPictureFromDBAsync(id) // takes 100ms
        val friends = fetchFriendsFromDBAsync(id) // takes 500ms
        return Profile(bio.await(), picture.await(), friends.await())
    }
    private fun fetchFriendsFromDBAsync(id: String) = scope.async {
        delay(500)
        emptyList<String>()
    }
    private fun fetchPictureFromDBAsync(id: String) =
        scope.async {
            delay(100)
            null
        }
    private fun fetchBioOverHttpAsync(id: String) = scope.async {
        delay(1000)
        "Just my"
    }
}