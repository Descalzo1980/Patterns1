package patterns.behavioral_patterns.mediator
// bad way
interface QA {
    fun doesMyCodeWork(): Boolean
}

interface Owl {
    fun isWatchingFootball(): Boolean
}

interface Parrot{
    fun isEating(): Boolean
    fun isSleeping(): Boolean
}

object Kenny : QA, Parrot {
    val developer = Me
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

object Brad : QA, Parrot {
    val senior = Kenny
    val developer = Me
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

object George : QA, Owl {
    val mate = Kenny
    val developer = Me


    override fun doesMyCodeWork(): Boolean {
        return true
    }

    override fun isWatchingFootball(): Boolean {
        return true
    }
}

object Me

object MyCompany {
    val cto = Me
    val qa = Kenny
    fun taskCompleted(){
        if(!qa.isEating() && !qa.isSleeping()){
            println(qa.doesMyCodeWork())
        }
    }
    val qa2 = Brad
    fun taskCompleted2(){
        if(!qa2.isEating() && !qa2.isSleeping()){
            println(qa2.doesMyCodeWork())
        }
    }
    val qa3 = George
    fun taskCompleted3(){
        if(!qa3.isWatchingFootball()){
            println(qa3.doesMyCodeWork())
        }
    }
}

/**************************************/

fun main() {
    val productManager = Michael
    val company = MyCompany1(productManager)
    company.taskCompleted(true)
}

interface ProductManager{
    fun isAllGood(majorRelease: Boolean): Boolean
}

object Michael: ProductManager{
    private val kenny = Kenny1(this)
    private val brad = Brad1(this)
    override fun isAllGood(majorRelease: Boolean): Boolean {
        if (!kenny.isEating() && !kenny.isSleeping()) {
            println(kenny.doesMyCodeWork())
        } else if (!brad.isEating() && !brad.isSleeping()) {
            println(brad.doesMyCodeWork())
        }
        return true
    }
}

class Kenny1(private val productManager: ProductManager) : QA, Parrot {
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class Brad1(private val productManager: ProductManager) : QA, Parrot {
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class MyCompany1(private val productManager: ProductManager) {
    fun taskCompleted(isMajorRelease: Boolean) {
        println(productManager.isAllGood(isMajorRelease))
    }
}