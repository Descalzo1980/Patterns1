package examples

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class UserName(val name: String)

inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json,object : TypeToken<T>() {}.type)
}



fun main(){
    val userJson = "{\"name\": \"Stas\"}"

    val user = Gson().fromJson<UserName>(userJson)

    println(user.name)
}