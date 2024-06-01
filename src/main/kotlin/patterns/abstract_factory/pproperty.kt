package patterns.abstract_factory

import java.lang.IllegalArgumentException

fun property(prop: String): Property{
    val (name,value) = prop.split(":")
    return when(name){
        "port" -> IntProperty(name, value.trim().toInt())
        "environment" -> StringProperty(name, value.trim())
        else -> throw IllegalArgumentException("Unknown property: $name")
    }
}

fun server(propertyString: List<String>): ServerConfiguration{
    val parsedProperties = mutableListOf<Property>()
    for (p in propertyString){
        parsedProperties += property(p)
    }
    return ServerConfigurationImpl(parsedProperties)
}


fun main(){
    val portProperty = property("port: 8080")
    val environmentProperty = property("environment: production")
    if(portProperty is IntProperty){
        val port: Int = portProperty.value
        println(port)
    }
    val port: Int? = portProperty.value as? Int
    if(port != null){
        val port: Int = port
        println(port)
    }
    println(portProperty is IntProperty)
    val port1: Int = portProperty.value as Int // unsafe
    val port2: Int = portProperty.value as? Int ?: 0
    println(port1)

    // Variable shadowing

    val portOrNull: Int? = portProperty.value as? Int
    if (portOrNull != null) {
        val port: Int = portOrNull
    }

    println(server(listOf("port: 8080", "environment: production")))
}