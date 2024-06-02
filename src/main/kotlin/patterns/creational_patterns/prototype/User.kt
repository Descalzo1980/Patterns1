package patterns.creational_patterns.prototype

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

fun createUser(name: String, role: Role, permissions: Set<Permission> = setOf(Permission.READ)) {
    val existingUser = allUsers.find { it.name == name }
    if (existingUser != null) {
        allUsers.remove(existingUser)
        val updatedPermissions = if (name == "Admin") {
            setOf(Permission.WRITE)
        } else {
            permissions
        }
        allUsers += existingUser.copy(role = role, permissions = updatedPermissions)
    } else {
        val newPermissions = if (name == "Admin") {
            setOf(Permission.WRITE)
        } else {
            permissions
        }
        allUsers += User(name = name, role = role, permissions = newPermissions)
    }
}

data class GraphicElement(
    val color: String,
    val points: List<String>,
    var texture: String? = null,
) {
    init {
        if (texture == null) {
            texture = "Some complex initialization logic here (loading textures, calculating geometry, etc.)"
        }
    }
}


fun main() {
    createUser(name = "First", role = Role.REGULAR_USER)
    createUser(name = "Admin", role = Role.ADMIN)
    createUser(name = "SuperAdmin", role = Role.SUPER_ADMIN)
    createUser("User1", role = Role.REGULAR_USER)
    createUser("User2", role = Role.REGULAR_USER)

    allUsers.forEach {
        println("Name: ${it.name}, Role: ${it.role}, Permissions: ${it.permissions}")
    }

    val initialPoints = listOf("x1", "y1", "x2", "y2")
    val originalElement = GraphicElement("Red", initialPoints)
    val clonedElement = originalElement.copy(color = "Blue")
    println(clonedElement)
}