class bisiesto {
    companion object{
        @JvmStatic
        fun main() {
            print("Introduce un año para comprobar si es bisiesto: ")
            val ano = readLine()?.toIntOrNull()

            if (ano != null) {
                // Es divisible por 4 Y NO por 100, O BIEN es divisible por 400.
                val esBisiesto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)

                if (esBisiesto) {
                    println("¡El año $ano es bisiesto!")
                } else {
                    println("El año $ano no es bisiesto.")
                }
            } else {
                println("Por favor, introduce un número de año válido.")
            }
        }
    }
}