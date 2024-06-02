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
    println(server(listOf("port: 8080", "environment: production")))
}