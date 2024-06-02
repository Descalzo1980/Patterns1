package patterns.prototype

data class User(
    val name: String,
    val role: Role,
    val permissions: Set<Permission>,
) {
    fun hasPermission(permission: Permission) = permission in permissions
}

enum class Role {
    ADMIN,
    SUPER_ADMIN,
    REGULAR_USER
}

enum class Permission {
    READ,
    WRITE;
}

val allUsers = mutableListOf<User>()
fun createUser(name: String, role: Role) {
    val existingUser = allUsers.find { it.role == role }
    allUsers += existingUser?.copy(name = name) ?:
    User(name = name, role = role, permissions = emptySet())
}

fun main(){
    createUser(name = "First", role = Role.REGULAR_USER)
    createUser(name = "Admin", role = Role.ADMIN)
    createUser(name = "SuperAdmin", role = Role.SUPER_ADMIN)
    val user = User(name = "Alice", role = Role.ADMIN, permissions = setOf(Permission.READ, Permission.WRITE))
    createUser("User1", role = Role.REGULAR_USER)
    createUser("User2", role = Role.REGULAR_USER)

    println(user.hasPermission(Permission.READ))
    println(user.hasPermission(Permission.WRITE))


    val permissionsAsString = user.permissions.map { it.name }
    println(permissionsAsString)

    allUsers.forEach{
        println("Name  ${it.name} Role ${it.role}")
    }
}