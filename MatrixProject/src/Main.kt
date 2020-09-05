import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main() {
    MatrixCalculator.startCalculator()
}

private object MatrixCalculator {

    fun startCalculator(){
        println("Welcome to the Matrix Calculator! Built by Ian Valdovinos\n" +
                "Insert any of the following commands:\n" +
                " 1) 2 by 2 inverse Matrix\n" +
                " 2) Matrix addition and subtraction\n" +
                " 3) Roll a dice\n" +
                " 4) Exit")

        while (true){
            println("Command Number: ")
            val command = readLine()?.toLowerCase()
            when (command){
                "1" -> inverseTwoByTwoMatrix()
                "4" -> {
                    println("Bye!")
                    exitProcess(0)
                }
                "2" -> matrixAdditionSubtraction()
                "3" -> diceRoll()
                else -> println("Invalid command.")
            }
        }
    }

    private fun inverseTwoByTwoMatrix(){
        println("Insert the 2 by 2 matrix:")

        try {
            val firstRow: MutableList<Double> = mutableListOf()
            val secondRow: MutableList<Double> = mutableListOf()

            val input1  = readLine()?.split(" ")
            val input2 = readLine()?.split(" ")

            input1?.let { list ->
                list.forEach {
                    firstRow.add(it.toDouble())
                }
            }

            input2?.let { list ->
                list.forEach {
                    secondRow.add(it.toDouble())
                }
            }

            val matrix = TwoByTwoMatrix(firstRow, secondRow)
            println("Inverse of the 2 by 2 matrix:")
            matrix.printInverse()

        } catch (e: Exception){
            println("The 2 by 2 matrix does not have the correct format.")
        }
    }

    private fun matrixAdditionSubtraction(){
        var rowNumber: Int = 0
        var columnNumber: Int = 0

        println("How many ROWS will the matrix hold?")
        val rowInput = readLine()
        println("How many COLUMNS will the matrix hold?")
        val columnInput = readLine()
        println("addition of subtraction?")
        val operation = readLine()?.toLowerCase()

        try {
            rowInput?.let {
                rowNumber = it.toInt()
            }
            columnInput?.let {
                columnNumber = it.toInt()
            }

            val matrix1 = Matrix(rowNumber, columnNumber)
            val matrix2 = Matrix(rowNumber, columnNumber)

            println("Enter matrix 1 with dimensions ${matrix1.rowsNumber}x${matrix1.columnsNumber}:")
            for (i in 0 until matrix1.rowsNumber) {
                val input = readLine()?.split(" ")?.toMutableList()
                require(input?.size == matrix1.columnsNumber)
                input?.let {
                    matrix1.rows.add(input)
                }
            }

            println("Enter matrix 2 with dimensions ${matrix2.rowsNumber}x${matrix2.columnsNumber}:")
            for (i in 0 until matrix2.rowsNumber) {
                val input = readLine()?.split(" ")?.toMutableList()
                require(input?.size == matrix2.columnsNumber)
                input?.let {
                    matrix2.rows.add(input)
                }
            }

            val resultMatrix = Matrix(matrix1.rowsNumber, matrix1.columnsNumber)

            operation?.let {
                when(operation){
                    "addition" -> Operation.resolveOperationAddition(matrix1, matrix2, resultMatrix, operation)
                    "subtraction" -> Operation.resolveOperationSubtraction(matrix1, matrix2, resultMatrix, operation)
                }
            }
        } catch (e: Exception){
            println("The matrix does not follow the correct format.")
        }
    }

    private fun diceRoll(){
        println("How many sides will the deice have?")
        val numSides = readLine()

        try{
            numSides?.let {
                val myDice = Dice(numSides.toInt())
                println("The dice rolls: ${myDice.roll()}")
            }
        } catch (e: Exception){
            println("Not a number")
        }

    }

    private object Operation{
        fun resolveOperationAddition(matrix1: Matrix, matrix2: Matrix, resultMatrix: Matrix, operation: String) {
            require(operation == "addition")

            for (row in 0 until matrix1.rowsNumber) {
                val resultRow = mutableListOf<String>()
                for (column in 0 until matrix1.columnsNumber) {
                    resultRow.add((matrix1.getElement(row, column) + matrix2.getElement(row, column)).toString())
                }
                resultMatrix.rows.add(resultRow)
            }

            resultMatrix.printMatrix()
        }

        fun resolveOperationSubtraction(matrix1: Matrix, matrix2: Matrix, resultMatrix: Matrix, operation: String) {
            require(operation == "subtraction")

            for (row in 0 until matrix1.rowsNumber) {
                val resultRow = mutableListOf<String>()
                for (column in 0 until matrix1.columnsNumber) {
                    resultRow.add((matrix1.getElement(row, column) - matrix2.getElement(row, column)).toString())
                }
                resultMatrix.rows.add(resultRow)
            }

            resultMatrix.printMatrix()
        }
        private fun Matrix.getElement(row: Int, column: Int) = this.rows[row].elementAt(column).toInt()
    }

    private class Dice(val numSides: Int){
        fun roll(): String = (1..numSides).shuffled().first().toString()
    }
}