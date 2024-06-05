package patterns.behavioral_patterns.command

fun main() {
    val t = Trooper()

    t.addOrder(moveGenerator(t, 1, 1))
    t.addOrder(moveGenerator(t, 2, 2))
    t.addOrder(moveGenerator(t, 3, 3))
    t.executeOrders()
    t.appendMove(0, 4)
        .appendMove(5, 4)
        .appendMove(5, 8)
        .appendMove(10, 8)
        .executeOrders()


    /*******************/

    val clipboard = Clipboard()
    val editor = TextEditor("Boo Lost")
    val invoker = TextEditorInvoker()

    invoker.executeCommand(CutCommand(editor, clipboard))
    invoker.executeCommand(CopyCommand(editor, clipboard))
    invoker.executeCommand(PasteCommand(editor, clipboard))

    editor.print()

    invoker.undo()

    editor.print()


    /*******************/

    val light = Light()
    val lightOnCommand = LightOnCommand(light)
    val lightOffCommand = LightOffCommand(light)
    val remote = RemoteControl(lightOnCommand)
    remote.pressButton()
    val remote2 = RemoteControl(lightOffCommand)
    remote2.pressButton()
}

interface CommandB{
    fun execute()
}

class Light {
    fun turnOn() {
        println("Light is ON")
    }
    fun turnOff() {
        println("Light is OFF")
    }
}

class LightOnCommand(private val light: Light): CommandB{
    override fun execute() {
        light.turnOn()
    }
}

class LightOffCommand(private val light: Light): CommandB{
    override fun execute() {
        light.turnOff()
    }
}

class RemoteControl(private val command: CommandB) {
    fun pressButton() {
        command.execute()
    }
}

/***************************************************************/

interface CommandA{
    fun execute()
    fun undo()
}

data class Clipboard(var content: String = "")

class TextEditor(initialContent: String) {
    private var content = initialContent

    fun cut(): String {
        val cutContent = content.takeLast(1)
        content = content.dropLast(1)
        return cutContent
    }

    fun copy(): String {
        return content
    }

    fun write(text: String) {
        content += text
    }

    fun delete(text: String) {
        content = content.removeSuffix(text)
    }

    fun print() {
        println(content)
    }
}

class CutCommand(private val receiver: TextEditor, private val clipboard: patterns.behavioral_patterns.command.Clipboard) : CommandA {
    override fun execute() {
        clipboard.content = receiver.cut()
    }

    override fun undo() {
        receiver.write(clipboard.content)
    }
}

class CopyCommand(private val receiver: TextEditor, private val clipboard: patterns.behavioral_patterns.command.Clipboard) : CommandA {
    override fun execute() {
        clipboard.content = receiver.copy()
    }

    override fun undo() {
        clipboard.content = ""
    }
}

class PasteCommand(private val receiver: TextEditor, private val clipboard: patterns.behavioral_patterns.command.Clipboard) : CommandA {
    override fun execute() {
        receiver.write(clipboard.content)
    }

    override fun undo() {
        receiver.delete(clipboard.content)
    }
}

class TextEditorInvoker {
    private val commands = mutableListOf<CommandA>()

    fun executeCommand(command: CommandA) {
        commands.add(command)
        command.execute()
    }

    fun undo() {
        if (commands.isNotEmpty()) {
            commands.removeLast().undo()
        }
    }
}

/****************************************/


open class Trooper {
    private val orders = mutableListOf<Command>()
    private val undoableOrders = mutableListOf<Pair<Command, Command>>()

    fun addOrder(order: Command) {
        this.orders.add(order)
    }
    fun executeOrders() {
        while (orders.isNotEmpty()) {
            val order = orders.removeFirst()
            order()
        }
    }
    fun appendMove(x: Int, y: Int): Trooper = apply {
        orders.add(moveGenerator(this, x, y))
        undoableOrders.add(moveGenerator(this, x, y) to moveGenerator(this, -x, -y))
    }
    fun move(x: Int, y: Int) {
        println("Moving to $x:$y")
    }
}

typealias Command = () -> Unit

val moveGenerator = fun(
    t: Trooper,
    x: Int,
    y: Int
): Command {
    return fun() {
        t.move(x, y)
    }
}