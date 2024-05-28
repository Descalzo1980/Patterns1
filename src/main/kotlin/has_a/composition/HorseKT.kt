package has_a.composition

import has_a.parent_class.HalterKT

class Horse {
    private val halter: HalterKT = HalterKT()
}

/**
 * Композиция - это тоже вид ассоциации, но с более тесной связью.
 * Объект одного класса полностью владеет объектом другого класса, и
 * объекты существуют внутри того же времени жизни.
 * Если удалить родительский объект, все
 * вложенные объекты будут удалены
 * */