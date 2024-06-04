package patterns.behavioral_patterns.strategy


enum class Direction {
    LEFT, RIGHT
}

data class Projectile(
    private val x: Int,
    private val y: Int,
    private val direction: Direction,
)
object Weapons {
    fun peashooter(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a peashooter")
        return Projectile(x, y, direction)
    }
    fun banana(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a banana")
        return Projectile(x, y, direction)
    }
    fun pomegranate(x: Int, y: Int, direction: Direction): Projectile {
        println("It's a pomegranate")
        return Projectile(x, y, direction)
    }
}
class OurHero {
    private val direction = Direction.LEFT
    private val x: Int = 42
    private val y: Int = 173

    var currentWeapon = Weapons::peashooter

    val shoot = fun() {
        currentWeapon(x, y, direction)
    }
}

fun main(){
    val hero = OurHero()
    hero.shoot()
    hero.currentWeapon = Weapons::banana
    hero.shoot()
    hero.currentWeapon = Weapons::pomegranate
    hero.shoot
/*    val discount = DiscountCalculator()
    val book = Book("Ololo",11.5)
    val customer = Customer("Boo", membershipType = MembershipType.REGULAR)
    val finalPrice = discount.calculateFinalPrice(book, customer)
    println("Price with discount =  $finalPrice")*/

    val book = Book("Effective Java", 100.0)
    val customer = Customer("John Doe", MembershipType.REGULAR)
    val discountCalculatorTest = DiscountCalculatorTest()
    val finalPrice = discountCalculatorTest.calculateFinalPrice(book, customer)
    println("FinalPrice: $$finalPrice")

    val array = arrayOf(34, 7, 23, 32, 5, 62)

    val context = SortContext(BubbleSortStrategy())
    println("Bubble Sort: " + context.executeStrategy(array).contentToString())

    context.setStrategy(QuickSortStrategy())
    println("Quick Sort: " + context.executeStrategy(array).contentToString())
}


/****************************************************************/


// Bad way
data class Book(val title: String, val price: Double)
data class Customer(val name: String, val membershipType: MembershipType)
enum class MembershipType {
    REGULAR, PREMIUM
}
class DiscountCalculator {
    private fun calculateDiscount(book: Book, customer: Customer): Double {
        return if (customer.membershipType == MembershipType.REGULAR) {
            book.price * 0.1
        } else {
            book.price * 0.2
        }
    }
    fun calculateFinalPrice(book: Book, customer: Customer): Double {
        val discount = calculateDiscount(book, customer)
        return book.price - discount
    }
}

// Good way
interface DiscountStrategy {
    fun calculateDiscount(book: Book): Double
}

class RegularCustomerDiscountStrategy : DiscountStrategy {
    override fun calculateDiscount(book: Book): Double {
        return book.price * 0.1
    }
}

class PremiumCustomerDiscountStrategy : DiscountStrategy {
    override fun calculateDiscount(book: Book): Double {
        return book.price * 0.2
    }
}

class DiscountCalculatorStrategy(private val discountStrategy: DiscountStrategy) {
    fun calculateDiscount(book: Book): Double {
        return discountStrategy.calculateDiscount(book)
    }
}

class DiscountCalculatorTest {
    private fun createDiscountCalculator(customer: Customer): DiscountCalculatorStrategy {
        val discountStrategy = when (customer.membershipType) {
            MembershipType.REGULAR -> RegularCustomerDiscountStrategy()
            MembershipType.PREMIUM -> PremiumCustomerDiscountStrategy()
        }
        return DiscountCalculatorStrategy(discountStrategy);
    }

    fun calculateFinalPrice(book: Book, customer: Customer): Double {
        val discountCalculator = createDiscountCalculator(customer)
        val discount = discountCalculator.calculateDiscount(book)
        return book.price - discount
    }
}

/************************************************************************/

interface SortStrategy {
    fun sort(array: Array<Int>): Array<Int>
}

class BubbleSortStrategy : SortStrategy {
    override fun sort(array: Array<Int>): Array<Int> {
        val arr = array.copyOf()
        for (i in arr.indices) {
            for (j in 0..<arr.size - 1 - i) {
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        return arr
    }
}

class QuickSortStrategy : SortStrategy {
    override fun sort(array: Array<Int>): Array<Int> {
        val arr = array.copyOf()
        quickSort(arr, 0, arr.size - 1)
        return arr
    }

    private fun quickSort(arr: Array<Int>, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)
            quickSort(arr, low, pi - 1)
            quickSort(arr, pi + 1, high)
        }
    }

    private fun partition(arr: Array<Int>, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low..<high) {
            if (arr[j] < pivot) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }
}

class SortContext(private var strategy: SortStrategy) {
    fun setStrategy(strategy: SortStrategy) {
        this.strategy = strategy
    }

    fun executeStrategy(array: Array<Int>): Array<Int> {
        return strategy.sort(array)
    }
}

fun sort(sortStrategy: SortStrategy, numbers: Array<Int>) {
    sortStrategy.sort(numbers)
}