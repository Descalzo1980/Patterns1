package patterns.designing_for_concurrency.barrier
import kotlin.random.Random
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    barrierWithSuccess()
    barrierWithFailure()
}

fun barrierWithFailure() {
    runBlocking {
        val characters: List<Deferred<FavoriteCharacter>> =
            listOf(
                Me.getFavoriteCharacter(),
                GrumpyCat.getFavoriteCharacter()
            )

        try {
            println(characters.awaitAll())
        } catch (e: RuntimeException) {
            println("Caught exception: ${e.message}")
        }
    }
}

fun barrierWithSuccess() {
    runBlocking {
        println(measureTimeMillis {
            fetchFavoriteCharacterWrong("Inigo Montoya")
        })
        println(measureTimeMillis {
            fetchFavoriteCharacterCorrect("Inigo Montoya")
        })
        val (name, catchphrase, _) = fetchFavoriteCharacterCorrect("Inigo Montoya")
        println("$name says: $catchphrase")
        val characters: List<Deferred<FavoriteCharacter>> =
            listOf(
                Me.getFavoriteCharacter(),
                Taylor.getFavoriteCharacter(),
                Michael.getFavoriteCharacter()
            )

        println(characters.awaitAll())
    }
}

suspend fun fetchFavoriteCharacterWrong(
    name: String
) = coroutineScope {
    val catchphrase = getCatchphraseAsync(name).await()
    val picture = getPictureAsync(name).await()

    FavoriteCharacter(name, catchphrase, picture)
}

suspend fun fetchFavoriteCharacterCorrect(
    name: String
) = coroutineScope {
    val catchphrase = getCatchphraseAsync(name)
    val picture = getPictureAsync(name)

    FavoriteCharacter(name, catchphrase.await(), picture.await())
}

data class FavoriteCharacter(
    val name: String,
    val catchphrase: String,
    val picture: ByteArray = Random.nextBytes(42)
)

fun CoroutineScope.getCatchphraseAsync(
    characterName: String
) = async {
    // Simulate network call
    delay(100)
    when (characterName) {
        "Inigo Montoya" -> "Hello. My name is Inigo Montoya. You killed my father. Prepare to die."
        else -> "No catchprase found"
    }
}

fun CoroutineScope.getPictureAsync(
    characterName: String
) = async {
    // Simulate network call
    delay(500)
    when (characterName) {
        else -> Random.nextBytes(42)
    }
}

object Michael {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("Terminator", "Hasta la vista, baby")
        }
    }
}

object Taylor {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("Don Vito Corleone", "I'm going to make him an offer he can't refuse")
        }
    }
}

object Me {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            // I already prepared the answer!
            FavoriteCharacter("Inigo Montoya", "Hello, my name is...")
        }
    }
}

object GrumpyCat {
    fun getFavoriteCharacter(): Deferred<FavoriteCharacter> =
        CompletableDeferred<FavoriteCharacter>().apply {
            completeExceptionally(RuntimeException("Grumpy cat likes no one"))
        }
}