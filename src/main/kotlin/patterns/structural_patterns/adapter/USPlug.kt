package patterns.structural_patterns.adapter

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.stream.Stream

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String
}

interface UsbMini {
    val hasPower: PowerState
}

enum class PowerState {
    TRUE, FALSE
}

interface UsbTypeC {
    val hasPower: Boolean
}

fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower) {
        println("I've Got The Power!")
    } else {
        println("No power")
    }
}

fun usPowerOutlet(): USPlug {
    return object : USPlug {
        override val hasPower = 1
    }
}

fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini {
        override val hasPower = if (plug.hasPower == "YES")
            PowerState.TRUE else PowerState.FALSE
    }
}

fun USPlug.toEUPlug(): EUPlug {
    val hasPower = if (this.hasPower == 1) "YES" else "NO"
    return object : EUPlug {
        override val hasPower = hasPower
    }
}

fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == PowerState.TRUE
    return object : UsbTypeC {
        override val hasPower = hasPower
    }
}

fun main(){
    cellPhone(
        charger(
            usPowerOutlet().toEUPlug()
        ).toUsbTypeC()
    )

    val modernPrinter = ModernPrinter()
    val legacyPrinter: Printer = ModernPrinterAdapter(modernPrinter)

    legacyPrinter.print()

    val userEntity = UserEntity(id = 1, username = "john_doe", email = "john.doe@example.com")
    val user = userEntity.toDomain()
    println(user)
    printStream(list.asSequence())
/*    val stream = Stream.generate { 42 }
    val result = stream.toList()
    println(result)*/

}
val list = listOf("a", "b", "c")
fun printStream(sequence: Sequence<String>) {
    sequence.forEach{ e -> println(e)}
}


//***********************************************************************

// Data class in the data layer
data class UserEntity(
    val id: Int,
    val username: String,
    val email: String
)

// Data class in the domain layer
data class User(
    val userId: Int = 0,
    val name: String = "",
    val emailAddress: String = ""
)

// Adapter function to convert UserEntity to User
fun UserEntity.toDomain(): User {
    return User(
        userId = this.id,
        name = this.username,
        emailAddress = this.email
    )
}

//***********************************************************************

interface MediaPlayer {
    fun play(audioType: String, fileName: String)
}

interface AdvancedMediaPlayer {
    fun playVlc(fileName: String)
    fun playMp4(fileName: String)
}

class VlcPlayer : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
        println("Playing vlc file. Name: $fileName")
    }

    override fun playMp4(fileName: String) {
    }
}

class Mp4Player : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
    }

    override fun playMp4(fileName: String) {
        println("Playing mp4 file. Name: $fileName")
    }
}

class MediaAdapter(audioType: String) : MediaPlayer {
    private var advancedMediaPlayer: AdvancedMediaPlayer? = null

    init {
        if (audioType.equals("vlc", true)) {
            advancedMediaPlayer = VlcPlayer()
        } else if (audioType.equals("mp4", true)) {
            advancedMediaPlayer = Mp4Player()
        }
    }

    override fun play(audioType: String, fileName: String) {
        if (audioType.equals("vlc", true)) {
            advancedMediaPlayer?.playVlc(fileName)
        } else if (audioType.equals("mp4", true)) {
            advancedMediaPlayer?.playMp4(fileName)
        }
    }
}

class AudioPlayer : MediaPlayer {
    private var mediaAdapter: MediaAdapter? = null

    override fun play(audioType: String, fileName: String) {
        if (audioType.equals("mp3", true)) {
            println("Playing mp3 file. Name: $fileName")
        } else if (audioType.equals("vlc", true) || audioType.equals("mp4", true)) {
            mediaAdapter = MediaAdapter(audioType)
            mediaAdapter?.play(audioType, fileName)
        } else {
            println("Invalid media. $audioType format not supported")
        }
    }
}

fun testAudioPlayer() {
    val outContent = ByteArrayOutputStream()
    System.setOut(PrintStream(outContent))
    val audioPlayer = AudioPlayer()
    audioPlayer.play("mp3", "fetch_water.mp3")
    outContent.reset()
    audioPlayer.play("mp4", "get_lost.mp4")
    outContent.reset()
    audioPlayer.play("vlc", "life_lessons.vlc")
    outContent.reset()
    audioPlayer.play("avi", "still_waters.avi")
}

//***********************************************************************

interface Printer {
    fun print()
}

class ModernPrinter {
    fun startPrint() {
        println("Printing in a modern way")
    }
}

class ModernPrinterAdapter(private val modernPrinter: ModernPrinter) : Printer {
    override fun print() {
        modernPrinter.startPrint()
    }
}