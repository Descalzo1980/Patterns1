package patterns.behavioral_patterns.interpreter

interface Expression {
    fun interpret(context: String): Boolean
}

class TerminalExpression(private val data: String): Expression{
    override fun interpret(context: String): Boolean {
        return context.contains(data)
    }
}

class OrExpression(private val expr1: Expression,private val expr2: Expression):Expression {
    override fun interpret(context: String): Boolean {
        return expr1.interpret(context) || expr2.interpret(context)
    }
}

class AndExpression(private val expr1: Expression, private val expr2: Expression) : Expression {
    override fun interpret(context: String): Boolean {
        return expr1.interpret(context) && expr2.interpret(context)
    }
}

fun getMaleExpression(): Expression {
    val john = TerminalExpression("John")
    val robert = TerminalExpression("Robert")
    return OrExpression(john, robert)
}
fun getMarriedWomanExpression(): Expression {
    val julie = TerminalExpression("Julie")
    val married = TerminalExpression("Married")
    return AndExpression(julie, married)
}

fun main() {
    val isMale = getMaleExpression()
    val isMarriedWoman = getMarriedWomanExpression()
    println("John is male? ${isMale.interpret("John")}")
    println("Julie is a married woman? ${isMarriedWoman.interpret("Married Julie")}")
}