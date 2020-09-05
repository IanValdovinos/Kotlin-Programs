
interface BasicMatrix {
    val rowsNumber: Int
    val columnsNumber: Int
    fun printMatrix()

}

data class TwoByTwoMatrix( val firstRow: List<Double>, val secondRow: List<Double>): BasicMatrix{
    override val rowsNumber= 2
    override val columnsNumber = 2

    override fun printMatrix(){
        println("$firstRow\n$secondRow")
    }

    fun printInverse() {
        if (inverse() != null){
            val inverseMatrix = inverse()
            inverseMatrix?.printMatrix()
        } else {
            println("The inverse of this matrix does not exist because the determinant is 0.")
        }
    }

    private fun determinant(): Double{
        val cross1 = firstRow[0] * secondRow[1]
        val cross2 = secondRow[0] * (firstRow[1] * (-1))

        return cross1 + cross2
    }

    private fun crossMatrix(): TwoByTwoMatrix {
        val row1 = listOf<Double>(secondRow[1], ((-1)*firstRow[1]))
        val row2 = listOf<Double>(((-1)*secondRow[0]), firstRow[0])
        return TwoByTwoMatrix(row1, row2)
    }

    private fun inverse(): TwoByTwoMatrix? {
        if (determinant() !=  0.0){
            val crossMatrix = crossMatrix()

            val row1 = mutableListOf<Double>()
            val row2 = mutableListOf<Double>()

            crossMatrix.firstRow.forEach{
                row1.add("%.2f".format(((1/determinant()) * it)).toDouble())
            }

            crossMatrix.secondRow.forEach{
                row2.add("%.2f".format(((1/determinant()) * it)).toDouble())
            }

            return TwoByTwoMatrix(row1.toList(), row2.toList())

        }

        return null
    }
}

data class Matrix(override val rowsNumber : Int, override val columnsNumber: Int): BasicMatrix{
    val rows: MutableList<MutableList<String>> = mutableListOf()

    override fun printMatrix() {
        rows.forEach{ numbers ->
            println(numbers)
        }
    }
}