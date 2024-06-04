package patterns.structural_patterns.facade

class AudioPlayer {
    fun playAudio() {
        println("Playing audio...")
    }

    fun stopAudio() {
        println("Stopping audio...")
    }
}

class VideoPlayer {
    fun playVideo() {
        println("Playing video...")
    }

    fun stopVideo() {
        println("Stopping video...")
    }
}

class MediaFacade(
    private val audioPlayer: AudioPlayer,
    private val videoPlayer: VideoPlayer
) {
    fun playMedia() {
        audioPlayer.playAudio()
        videoPlayer.playVideo()
    }

    fun stopMedia() {
        audioPlayer.stopAudio()
        videoPlayer.stopVideo()
    }
}

fun main() {
    val audioPlayer = AudioPlayer()
    val videoPlayer = VideoPlayer()
    val mediaFacade = MediaFacade(audioPlayer, videoPlayer)

    mediaFacade.playMedia()
    mediaFacade.stopMedia()
}