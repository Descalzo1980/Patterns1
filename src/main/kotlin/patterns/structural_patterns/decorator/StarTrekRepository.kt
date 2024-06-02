package patterns.structural_patterns.decorator

open class StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

    open fun getCaptain(starshipName: String): String{
        return starshipCaptains[starshipName] ?: "Unknown"
    }

/*    open fun getCaptain(starshipName: String): String? {
        return if (starshipCaptains[starshipName] != null){
            starshipCaptains[starshipName]
        }else{
            "Unknown"
        }
    }*/

    open fun addCaptain(starshipName: String,captainName: String){
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptainStarTrekRepository : StarTrekRepository() {
    override fun getCaptain(starshipName: String): String {
        println("Getting captain for $starshipName")
        return super.getCaptain(starshipName)
    }
}

class ValidatingAddCaptainStarTrekRepository: StarTrekRepository() {
    override fun addCaptain(starshipName: String, captainName: String) {
        if(captainName.length > 15){
            throw RuntimeException("$captainName is longer than 15 characters!")
        }
        super.addCaptain(starshipName, captainName)
    }
}
//********************************************************************************

/*interface StarTrekRepository1 {
    fun getCaptain1(starshipName: String): String
    fun addCaptain1(starshipName: String, captainName: String)
}*/

class DefaultStarTrekRepository(): StarTrekRepository2 {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to
            "Jean-Luc Picard")
    override fun get(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    override fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: StarTrekRepository2):
    StarTrekRepository2 by repository {
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository[starshipName]
    }
}

class ValidatingAdd(private val repository: StarTrekRepository2):
        StarTrekRepository2 by repository {
            private val maxNameLength = 15
    override fun set(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength){
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository[starshipName] = captainName
    }
}

interface StarTrekRepository2 {
    operator fun get(starshipName: String): String
    operator fun set(starshipName: String, captainName: String)
}

fun main(){
    val starTrekRepository1 = DefaultStarTrekRepository()
    val withValidator = ValidatingAdd(starTrekRepository1)
    val withLoggingAndValidating = LoggingGetCaptain(withValidator)
    println(withLoggingAndValidating["USS Enterprise"])
    val captain = withLoggingAndValidating["USS Enterprise"]
/*    withLoggingAndValidating["USS Voyager"] = "Kathryn Janeway"*/

    christmasTreeWithGarlands()
    christmasTreeWithBubbleLights()
}

//********************************************************************************


interface ChristmasTree {
    fun decorate(): String
}

class PineChristmasTree : ChristmasTree {
    override fun decorate() = "Christmas tree"
}

abstract class TreeDecorator(private val tree: ChristmasTree) : ChristmasTree {
    override fun decorate(): String {
        return tree.decorate()
    }
}

class BubbleLights(tree: ChristmasTree) : TreeDecorator(tree) {
    override fun decorate(): String {
        return super.decorate() + decorateWithBubbleLights()
    }
    private fun decorateWithBubbleLights(): String {
        return " with Bubble Lights"
    }
}

fun christmasTreeWithBubbleLights() {
    val christmasTree = BubbleLights(PineChristmasTree())
    val decoratedChristmasTree = christmasTree.decorate()
    println(decoratedChristmasTree)
}
// другой подход через делегат
class Garlands(private val tree: ChristmasTree) : ChristmasTree by tree {
    override fun decorate(): String {
        return tree.decorate() + decorateWithGarlands()
    }
    private fun decorateWithGarlands(): String {
        return " with Garlands"
    }
}

fun christmasTreeWithGarlands() {
    val christmasTree = Garlands(PineChristmasTree())
    val decoratedChristmasTree = christmasTree.decorate()
    println(decoratedChristmasTree)
}



