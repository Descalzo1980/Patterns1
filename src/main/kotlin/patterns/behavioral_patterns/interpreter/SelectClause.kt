package patterns.behavioral_patterns.interpreter

@DslMarker
annotation class SqlDslMarker

@SqlDslMarker
class SelectClause(private vararg val columns: String) {
    private lateinit var from: FromClause
    fun from(
        table: String,
        where: FromClause.() -> Unit
    ): FromClause {
        this.from = FromClause(table)
        return this.from.apply(where)
    }
    override fun toString() = "SELECT ${columns.joinToString(separator =
    ", ")} $from"
}
@SqlDslMarker
class FromClause(private val table: String) {
    private lateinit var where: WhereClause
    fun where(conditions: String) = this.apply {
        where = WhereClause(conditions)
    }
    override fun toString() = "FROM $table $where"
}
@SqlDslMarker
class WhereClause(private val conditions: String) {
    override fun toString() = "WHERE $conditions"
}

fun select(vararg columns: String, from: SelectClause.()->Unit):
        SelectClause {
    return SelectClause(*columns).apply(from)
}

fun main() {
    val query = select("name", "age") {
        from("users") {
            where("age > 18")
        }
    }
    println(query)
}