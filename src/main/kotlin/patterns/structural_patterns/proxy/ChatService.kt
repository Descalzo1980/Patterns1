package patterns.structural_patterns.proxy

interface ChatService {
    fun sendMessage(message: String)
    fun getMessage(): String
}

interface Encoder {
    fun encode(message: String): String
    fun decode(message: String): String
}
class DefaultChatService : ChatService {
    private val messageStore: MutableList<String> = mutableListOf()
    override fun sendMessage(message: String) {
        println("Sending message: $message")
        messageStore.add(message)
    }
    override fun getMessage(): String {
        return if (messageStore.isNotEmpty()) {
            messageStore.removeAt(0)
        } else {
            "No messages"
        }
    }
}
class SimpleEncoder : Encoder {
    override fun encode(message: String): String {
        return message.reversed()
    }
    override fun decode(message: String): String {
        return message.reversed()
    }
}
class ChatServiceSecureProxy(private val encoder: Encoder) : ChatService {
    private val chatService: ChatService = DefaultChatService()
    override fun sendMessage(message: String) {
        val encodedMessage = encoder.encode(message)
        chatService.sendMessage(encodedMessage)
        println("Encoded message sent: $encodedMessage")
    }
    override fun getMessage(): String {
        val message = chatService.getMessage()
        val decodedMessage = encoder.decode(message)
        println("Encoded message received: $message")
        println("Decoded message: $decodedMessage")
        return decodedMessage
    }
}
fun main() {
    val encoder = SimpleEncoder()
    val secureChatService = ChatServiceSecureProxy(encoder)
    secureChatService.sendMessage("Hello, World!")
    val receivedMessage = secureChatService.getMessage()
    println("Received message: $receivedMessage")
}