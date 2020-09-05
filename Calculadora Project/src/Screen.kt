import java.lang.Exception
import kotlin.system.exitProcess

fun main() {

    while (true){
        val userInput = readLine()
        if (userInput?.toLowerCase() == "exit")   exitProcess(0)
        try{
            userInput?.let{
                val (num1, operator, num2) = it.split(" ")
                println(processInput(num1.toInt(), num2.toInt(), operator))
            }
        }catch (e: Exception){
            println("I cannot do the calculation.")
        }
    }

}

private fun processInput(num1: Int, num2: Int, operator: String): Int{
    val operators = listOf<String>("+","-","x","/")
    require(operators.contains(operator) || operators.contains(num2.toString())) {"Invalid operator."}
    return when(operator){
        "+" -> num1 + num2
        "-" -> num1 - num2
        "/" -> num1 / num2
        "x" -> num1 * num2
        else -> 0
    }
}
