package patterns.builder
// bad
data class RegularMail(
    val to: List<String>,
    val cc: List<String>?,
    val title: String?,
    val message: String?,
    val important: Boolean,
)

fun main(){
    val mail = RegularMail(
        listOf("mandatory@gmail.com"),
        null,
        "Ping",
        null,
        true
    )
    println(mail)

    val mailBuilder = MailBuilder()
        .toWhom(listOf("Some"))
        .recipients(listOf("mandatory@gmail.com"))
        .message("Hello mailBuilder")
        .build()

    println(mailBuilder)

    val mailFluent = MailFluent((listOf("Some"))).apply {
        message("Hello mailFluent")
        cc(listOf("mandatory@gmail.com"))
    }
    println(mailFluent)
    val mailFluent1 = MailFluent(listOf("mandatory@gmail.com")).message("Hello mailFluent")

    println(mailFluent1)

    val mailApply = MailApply(listOf("manager@company.com")).apply {
        message = "You've been promoted"
        title = "Come to my office"
    }

    println(mailApply)

    val mailDefaults = MailDefaults(
        title = "Hello",
        message = "There",
        important = true,
        to = listOf("my@dear.cat")
    )

    println(mailDefaults)
}

// cool
class MailBuilder {
    private var toWhom: List<String> = listOf()
    private var cc: List<String> = listOf()
    private var title: String = ""
    private var message: String = ""
    private var important: Boolean = false
    data class Mail internal constructor(
        val toWhom: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val important: Boolean
    )
    fun build(): Mail {
        if(toWhom.isEmpty()){
            throw RuntimeException("To property is empty")
        }
        return Mail(toWhom, cc, title, message, important)
    }

    fun message(message: String): MailBuilder{
        this.message = message
        return this
    }

    fun recipients(cc: List<String>): MailBuilder {
        this.cc = cc
        return this
    }

    fun toWhom(toWhom: List<String>): MailBuilder {
        this.toWhom = toWhom
        return this
    }
}


data class MailFluent(
    val to: List<String>,
    private var _message: String? = null,
    private var _cc: List<String>? = null,
    private var _title: String? = null,
    private var _important: Boolean? = null
) {
    fun message(message: String) = apply {
        _message = message
    }

    fun cc(cc: List<String>) = apply {
        _cc = cc
    }
}

data class MailApply(
    val to: List<String>,
    var message: String? = null,
    var cc: List<String>? = null,
    var title: String? = null,
    var important: Boolean? = null
)

data class MailDefaults(
    val to: List<String>,
    val cc: List<String> = listOf(),
    val title: String = "",
    val message: String = "",
    val important: Boolean = false,
)




