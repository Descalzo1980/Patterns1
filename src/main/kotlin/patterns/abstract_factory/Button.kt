package patterns.abstract_factory

interface Button {
    fun paint()
}

class MacBookButton: Button {
    override fun paint() {
        println("This is MacBook Button")
    }
}

class WinButton: Button {
    override fun paint() {
        println("This is a WinButton")
    }
}

interface CheckBox{
    fun paint()
}

class MacBookCheckBox: CheckBox {
    override fun paint() {
        println("This is MacBook CheckBox")
    }
}

class WinButtonCheckBox: CheckBox {
    override fun paint() {
        println("This is a CheckBox")
    }
}

interface GUIFactory{
    fun createButton(): Button
    fun createCheckBox(): CheckBox
}

class WinFactory: GUIFactory{
    override fun createButton(): Button {
        return WinButton()
    }

    override fun createCheckBox(): CheckBox {
        return WinButtonCheckBox()
    }
}

class MacFactory: GUIFactory{
    override fun createButton(): Button {
        return MacBookButton()
    }

    override fun createCheckBox(): CheckBox {
        return MacBookCheckBox()
    }
}

fun main() {
    val winFactory: GUIFactory = WinFactory()
    val macFactory: GUIFactory = MacFactory()

    val winButton: Button = winFactory.createButton()
    val winCheckBox: CheckBox = winFactory.createCheckBox()
    val macButton: Button = macFactory.createButton()
    val macCheckBox: CheckBox = macFactory.createCheckBox()

    winButton.paint()
    winCheckBox.paint()
    macButton.paint()
    macCheckBox.paint()
}
