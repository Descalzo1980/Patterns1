package patterns.creational_patterns.abstract_factory

// Abstract Factory Interface
interface ProductFactory {
    fun createProduct(): Product
    fun createInventory(): Inventory
    fun createShipping(): Shipping
}

// Concrete Factory for Electronics
class ElectronicsFactory : ProductFactory {
    override fun createProduct(): Product {
        return ElectronicsProduct()
    }

    override fun createInventory(): Inventory {
        return ElectronicsInventory()
    }

    override fun createShipping(): Shipping {
        return ElectronicsShipping()
    }
}

// Concrete Factory for Clothing
class ClothingFactory : ProductFactory {
    override fun createProduct(): Product {
        return ClothingProduct()
    }

    override fun createInventory(): Inventory {
        return ClothingInventory()
    }

    override fun createShipping(): Shipping {
        return ClothingShipping()
    }
}

// Abstract Product Interface
interface Product {
    fun process()
}

// Concrete Product for Electronics
class ElectronicsProduct : Product {
    override fun process() {
        println("Processing Electronics product.")
    }
}

// Concrete Product for Clothing
class ClothingProduct : Product {
    override fun process() {
        println("Processing Clothing product.")
    }
}

// Abstract Inventory Interface
interface Inventory {
    fun track()
}

// Concrete Inventory for Electronics
class ElectronicsInventory : Inventory {
    override fun track() {
        println("Tracking Electronics inventory.")
    }
}

// Concrete Inventory for Clothing
class ClothingInventory : Inventory {
    override fun track() {
        println("Tracking Clothing inventory.")
    }
}

// Abstract Shipping Interface
interface Shipping {
    fun ship()
}

// Concrete Shipping for Electronics
class ElectronicsShipping : Shipping {
    override fun ship() {
        println("Shipping Electronics product.")
    }
}

// Concrete Shipping for Clothing
class ClothingShipping : Shipping {
    override fun ship() {
        println("Shipping Clothing product.")
    }
}

// OrderProcessor using Abstract Factory
class OrderProcessor(factory: ProductFactory) {
    private val product = factory.createProduct()
    private val inventory = factory.createInventory()
    private val shipping = factory.createShipping()

    fun processOrder() {
        product.process()
        inventory.track()
        shipping.ship()
    }
}

fun main() {
    val electronicsFactory = ElectronicsFactory()
    val clothingFactory = ClothingFactory()

    val electronicsOrderProcessor = OrderProcessor(electronicsFactory)
    val clothingOrderProcessor = OrderProcessor(clothingFactory)

    electronicsOrderProcessor.processOrder()
    clothingOrderProcessor.processOrder()
}