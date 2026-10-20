import ecuacion.Companion.resolverEcuacionSegundoGrado

class mayorMenorMediano {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ordenarTresNumeros(15, 42, 8);
        }
        fun ordenarTresNumeros(a: Int, b: Int, c: Int) {
            val mayor: Int
            val mediano: Int
            val menor: Int

            if (a >= b && a >= c) {
                mayor = a
                if (b >= c) {
                    mediano = b
                    menor = c
                } else {
                    mediano = c
                    menor = b
                }
            } else if (b >= a && b >= c) {
                mayor = b
                if (a >= c) {
                    mediano = a
                    menor = c
                } else {
                    mediano = c
                    menor = a
                }
            } else {
                mayor = c
                if (a >= b) {
                    mediano = a
                    menor = b
                } else {
                    mediano = b
                    menor = a
                }
            }

            println("Mayor: $mayor, Mediano: $mediano, Menor: $menor")
        }
    }
}