
fun main() {
    val ladoValido = "aguila"
    var juegoAcabado = false
    var cuenta = 0

    val listaDeGente: MutableList<Persona> = mutableListOf()
    val listaDeGenteEliminada: MutableList<Persona> = mutableListOf()

    for (nombre in 0..999) {
        listaDeGente.add(Persona(nombre + 1))
    }

    while(!juegoAcabado){
        rondaGirarMoneda(listaDeGente, ladoValido, listaDeGenteEliminada)
        cuenta++

        when (listaDeGente.size){
            1 -> {
                println("${listaDeGente[0].nombre} tiro aguila $cuenta veces seguidas. ${listaDeGente[0].nombre} es suertud@.")
                juegoAcabado = true
            }
            0 -> {
                println("Los ultimos jugadores fueron eliminados en el round numero $cuenta")
                juegoAcabado = true
            }
        }
    }
}

private fun rondaGirarMoneda(
    listaDeGente: MutableList<Persona>,
    ladoValido: String,
    listaDeGenteEliminada: MutableList<Persona>
) {
    listaDeGente.forEach { persona ->
        val lado = persona.tirarMoneda()
        if (lado != ladoValido) {
            listaDeGenteEliminada.add(persona)
        }
    }

    listaDeGenteEliminada.forEach() { personaEliminada ->
        listaDeGente.remove(personaEliminada)
    }
    //imprimirResultados(listaDeGenteEliminada, listaDeGente)
}

private fun imprimirResultados(
    listaDeGenteEliminada: MutableList<Persona>,
    listaDeGente: MutableList<Persona>
) {
    println(
        """
        ${listaDeGenteEliminada.size} personas fueron eliminadas.
        A ${listaDeGente.size} le salio aguila.
    """.trimIndent()
    )
}

class Persona(
    val nombre: Int
) {
    private val moneda: List<String> = listOf("sol", "aguila")

    fun tirarMoneda(): String {
        val lado = moneda.random()
        //println("A $nombre le salio $lado.")
        return lado
    }
}