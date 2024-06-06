package patterns.reactive_and_concurrent_patterns.fp.pattern_matching

fun main(){

    println(getSound(Cat()))
    val animal = Cat()
    println(animal.getSound(animal))
}

fun getSound(animal: Animal): String = when(animal){
    is Cat -> animal.purr()
    is Dog -> animal.bark()
    else -> throw RuntimeException("Unknown animal")
}

interface Animal {
    fun getSound(animal: Animal): String {
        var sound: String? = null;
        if (animal is Cat) {
            sound = animal.purr();
        } else if (animal is Dog) {
            sound = animal.bark();
        }
        if (sound == null) {
            throw RuntimeException();
        }
        return sound;
    }
}

class Cat : Animal {
    fun purr(): String {
        return "Purr-purr";
    }
}

class Dog : Animal {
    fun bark(): String {
        return "Bark-bark";
    }
}