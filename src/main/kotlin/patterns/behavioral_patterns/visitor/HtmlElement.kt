package patterns.behavioral_patterns.visitor

fun main() {
    val page = Page(
        Container(
            Image,
            Link,
            Image
        ),
        Table,
        Link,
        Container(
            Table,
            Link
        ),
        Container(
            Image,
            Container(
                Image,
                Link
            )
        )
    )
    println(collectLinks(page))


    val cart = Cart()
    cart.addListing(Listing("Listing 1", 10.0))
    cart.addListing(Listing("Listing 2", 20.0))
    cart.addListing(Listing("Listing 3", 30.0))
    val visitor = ShoppingCartVisitorImpl()
    val totalPrice = cart.accept(visitor)
    println(totalPrice)
    cart.removeListing(Listing("Listing 2", 20.0))
    val nextPrice = cart.accept(visitor)
    println(nextPrice)


    val result = AdditionExpression(
        left = DoubleExpression(1.0),
        right = AdditionExpression(
            left = DoubleExpression(2.0),
            right = DoubleExpression(3.0)
        )
    )
    println(result)

    val elements: List<Element> = listOf(ConcreteElementA(), ConcreteElementB())
    val visitors: List<Visitor> = listOf(ConcreteVisitor1(), ConcreteVisitor2())

    for (element in elements) {
        for (v in visitors) {
            element.accept(v)
        }
    }


    val visitorBody: VisitorBody = ExportToCSVVisitor()
    val bodyParts = listOf(
        Eye("blue"),
        Eye("borwn"),
        Mouth(20),
    )
    bodyParts.forEach { it.accept(visitorBody) }
}

interface VisitorBody {
    fun visitEye(eye: Eye)
    fun visitMouth(mouth: Mouth)
}

interface BodyPart {
    fun accept(visitor: VisitorBody)
}

class Eye(val color: String) : BodyPart {
    override fun accept(visitor: VisitorBody) {
        visitor.visitEye(this)
    }
}

class Mouth(val size: Int) : BodyPart {
    override fun accept(visitor: VisitorBody) {
        visitor.visitMouth(this)
    }
}

class ExportToCSVVisitor : VisitorBody {
    override fun visitEye(eye: Eye) {
        println("csv eye: ${eye.color}")
    }

    override fun visitMouth(mouth: Mouth) {
        println("csv mouth: ${mouth.size}")
    }
}


/**********************************************/

abstract class Expression{
    abstract override fun toString(): String
}

class DoubleExpression(private val value: Double) : Expression() {
    override fun toString(): String {
        return value.toString()
    }
}

class AdditionExpression(
    private val left: Expression,
    private val right: Expression
) : Expression() {
    override fun toString(): String {
        return "(${left} + ${right})"
    }
}
interface Element {
    fun accept(visitor: Visitor)
}

class ConcreteElementA : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun operationA(): String {
        return "ElementA"
    }
}

class ConcreteElementB : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun operationB(): String {
        return "ElementB"
    }
}

interface Visitor {
    fun visit(element: ConcreteElementA)
    fun visit(element: ConcreteElementB)
}

class ConcreteVisitor1 : Visitor {
    override fun visit(element: ConcreteElementA) {
        println("ConcreteVisitor1 visiting ${element.operationA()}")
    }

    override fun visit(element: ConcreteElementB) {
        println("ConcreteVisitor1 visiting ${element.operationB()}")
    }
}

class ConcreteVisitor2 : Visitor {
    override fun visit(element: ConcreteElementA) {
        println("ConcreteVisitor2 visiting ${element.operationA()}")
    }

    override fun visit(element: ConcreteElementB) {
        println("ConcreteVisitor2 visiting ${element.operationB()}")
    }
}
/******************************************/

interface ShoppingCartVisitor {
    fun visit(listing: Listing): Double
    fun visit(cart: Cart): Double
}

class ShoppingCartVisitorImpl : ShoppingCartVisitor {
    override fun visit(listing: Listing): Double {
        return listing.price
    }

    override fun visit(cart: Cart): Double {
        var totalPrice = 0.0
        for (listing in cart.listings) {
            totalPrice += listing.accept(this)
        }
        return totalPrice
    }
}


interface Visitable {
    fun accept(visitor: ShoppingCartVisitor): Double
}

data class Listing(val name: String, val price: Double): Visitable {
    override fun accept(visitor: ShoppingCartVisitor): Double {
        return visitor.visit(this)
    }
}

class Cart: Visitable {
    val listings = mutableListOf<Listing>()

    fun addListing(listing: Listing) {
        listings.add(listing)
    }

    fun removeListing(listing: Listing) {
        listings.remove(listing)
    }

    override fun accept(visitor: ShoppingCartVisitor): Double {
        return visitor.visit(this)
    }
}

/****************************************/



fun collectLinks(page: Page): List<String> {
    return LinksCrawler().run {
        page.accept(this)
        this.links
    }
}


class LinksCrawler {
    private var _links = mutableListOf<String>()

    val links
        get() = _links.toList()

    fun visit(page: Page) {
        visit(page.elements)
    }

    fun visit(container: Container) = visit(container.elements)

    private fun visit(elements: List<HtmlElement>) {
        for (e in elements) {
            when (e) {
                is Container -> e.accept(this)
                is Link -> _links.add(e.href)
                is Image -> _links.add(e.src)
                else -> {
                }
            }
        }
    }
}

private fun Container.accept(feature: LinksCrawler) {
    feature.visit(this)
}

private fun Page.accept(feature: LinksCrawler) = feature.visit(this)

class Page(val elements: MutableList<HtmlElement> = mutableListOf()) {
    constructor(vararg elements: HtmlElement) : this(mutableListOf()) {
        for (s in elements) {
            this.elements.add(s)
        }
    }
}


sealed class HtmlElement

class Container(val elements: MutableList<HtmlElement> = mutableListOf()) : HtmlElement() {

    constructor(vararg units: HtmlElement) : this(mutableListOf()) {
        for (u in units) {
            this.elements.add(u)
        }
    }
}

data object Image : HtmlElement() {
    val src: String
        get() = "https://some.image"
}

data object Link : HtmlElement() {
    val href: String
        get() = "https://some.link"
}

data object Table : HtmlElement()