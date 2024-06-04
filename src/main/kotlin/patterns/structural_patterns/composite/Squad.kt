package patterns.structural_patterns.composite

import patterns.structural_patterns.bridge.*

class Squad(private val units: List<TrooperNew>): TrooperNew{
    constructor(): this(listOf())
    constructor(t1: TrooperNew): this(listOf(t1))
    constructor(t1: TrooperNew, t2: TrooperNew): this(listOf(t1,
        t2))
    override fun move(x: Long, y: Long){
        for (u in units){
            u.move(x, y)
        }
    }

    override fun attackRebel(x: Long, y: Long){
        for (u in units){
            u.attackRebel(x,y)
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

fun main(){
    val bobaFett = StormTrooperNew(Rifle(), RegularLegs())
    val squad = Squad(listOf(bobaFett.copy(), bobaFett.copy(), bobaFett.
    copy()))
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
}