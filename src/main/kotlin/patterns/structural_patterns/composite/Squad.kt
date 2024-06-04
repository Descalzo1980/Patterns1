package patterns.structural_patterns.composite

import patterns.structural_patterns.bridge.*
import java.lang.StringBuilder

class Squad(private val units: List<TrooperNew>) : TrooperNew {
    constructor() : this(listOf())
    constructor(t1: TrooperNew) : this(listOf(t1))
    constructor(t1: TrooperNew, t2: TrooperNew) : this(
        listOf(
            t1,
            t2
        )
    )

    override fun move(x: Long, y: Long) {
        for (u in units) {
            u.move(x, y)
        }
    }

    override fun attackRebel(x: Long, y: Long) {
        for (u in units) {
            u.attackRebel(x, y)
        }
    }

    override fun retreat() {
        super.retreat()
    }
}

//*******************************************************************


abstract class Component(val name: String) {
    abstract fun display()
}

class CompositeComponent(name: String) : Component(name) {
    private val components: MutableList<Component> = mutableListOf()

    fun add(component: Component) {
        components.add(component)
    }

    fun remove(component: Component) {
        components.remove(component)
    }

    override fun display() {
        println("Composite Component: $name")
        components.forEach { component ->
            component.display()
        }
    }
}

class BranchComponent(name: String) : Component(name) {
    override fun display() {
        println("Branch Component: $name")
    }
}

interface MovieComponent {
    fun play(): String
    fun stop(): String
}

class Movie(private val name: String) : MovieComponent {
    override fun play(): String {
        return "Playing movie: $name\n"
    }

    override fun stop(): String {
        return "Stop movie: $name\n"
    }
}

class Playlist(private val name: String) : MovieComponent {
    private val movieComponents = mutableListOf<MovieComponent>()

    fun add(movieComponent: MovieComponent) {
        movieComponents.add(movieComponent)
    }

    override fun play(): String {
        val result = StringBuilder()
        result.append("Playing playlist: $name\n")
        for (movieComponent in movieComponents) {
            result.append(movieComponent.play())
        }
        return result.toString()
    }

    override fun stop(): String {
        val result = StringBuilder()
        result.append("Stopping playlist: $name\n")
        for (movieComponent in movieComponents) {
            result.append(movieComponent.stop())
        }
        return result.toString()
    }
}

// Component
interface Employee {
    fun showDetails(): String
}
// Leaf
class Developer(private val name: String, private val position: String) : Employee {
    override fun showDetails() = "Developer: $name, Position: $position"
}
// Composite
class Manager(private val name: String, private val position: String) : Employee {
    private val employees: MutableList<Employee> = mutableListOf()
    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }
    fun removeEmployee(employee: Employee) {
        employees.remove(employee)
    }
    override fun showDetails(): String {
        val employeeDetails = employees.joinToString("\n") { it.showDetails() }
        return "Manager: $name, Position: $position\n$employeeDetails"
    }
}

fun main() {

    val dev1 = Developer("John Doe", "Frontend Developer")
    val dev2 = Developer("Jane Smith", "Backend Developer")
    val dev3 = Developer("Jane Smith", "Backend Developer")
    val manager = Manager("Ella White", "Tech Lead")
    manager.addEmployee(dev1)
    manager.addEmployee(dev2)
    manager.removeEmployee(dev3)
    println(manager.showDetails())


    val bobaFett = StormTrooperNew(Rifle(), RegularLegs())
    val squad = Squad(
        listOf(
            bobaFett.copy(), bobaFett.copy(), bobaFett.copy()
        )
    )
    val platoon = Squad(Squad(), Squad())

    val headOffice = CompositeComponent("Head Office")
    val region1 = CompositeComponent("Region 1")
    val region2 = CompositeComponent("Region 2")
    val branch1 = BranchComponent("Bracnch 1")
    val branch2 = BranchComponent("Bracnch 2")
    val branch3 = BranchComponent("Branch 3")

    headOffice.add(region1)
    headOffice.add(region2)
    region1.add(branch1)
    region1.add(branch2)
    region2.add(branch3)

    headOffice.display()


    val actionMoviesPlayList = Playlist("Action Movies")
    actionMoviesPlayList.add(Movie("The Matrix"))
    actionMoviesPlayList.add(Movie("Die Hard"))

    val comicMoviesPlayList = Playlist("Comic Movies")
    comicMoviesPlayList.add(Movie("The Hangover"))
    comicMoviesPlayList.add(Movie("Bridesmaids"))

    val allPlaylists = Playlist("All Playlists")
    allPlaylists.add(actionMoviesPlayList)
    allPlaylists.add(comicMoviesPlayList)

    val playResult = allPlaylists.play()

    val stopResult = allPlaylists.stop()

    println(playResult)
    println(stopResult)
}

