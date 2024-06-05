package dsl

class Person {
    var name: String = ""
    var age: Int = 0
    var city: String = ""

    override fun toString(): String {
        return "Person(name='$name', age=$age, city='$city')"
    }
}

fun buildPerson(config: Person.() -> Unit): Person {
    val person = Person()
    person.config()
    return person
}

fun buildPersonNoReciever(config: (Person) -> Unit): Person {
    val person = Person()
    config(person)
    return person
}

fun main() {
    val person = buildPerson {
        name = "John Doe"
        age = 30
        city = "New York"
    }
    println(person)

    val personNoReciever = buildPersonNoReciever { personNo ->
        personNo.name = "John Doe"
        personNo.age = 30
        personNo.city = "New York"
    }
    println(personNoReciever)
}