import kotlin.math.sqrt

class ecuacion {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var num1 = 4;
            var num2 = 6;
            var num3 = 2;

            println(resolverEcuacionSegundoGrado(num1, num2, num3 ));
        }
        fun resolverEcuacionSegundoGrado(a: Int, b: Int, c: Int): Pair<Double, Double>? {
            if (a == 0) {
                throw IllegalArgumentException("El coeficiente 'a' no puede ser 0 en una ecuación de segundo grado.")
            }

            val discriminante = b * b - 4 * a * c

            if (discriminante < 0) {
                return null
            }

            val raizDiscriminante = sqrt(discriminante.toDouble().toDouble())
            val x1 = (-b + raizDiscriminante) / (2 * a)
            val x2 = (-b - raizDiscriminante) / (2 * a)

            return Pair(x1, x2)
        }
    }
}