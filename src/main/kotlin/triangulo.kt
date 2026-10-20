import kotlin.math.sqrt
class triangulo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var continuar="s";
            var contador=0;

            while(continuar=="s") {
                print("introduce el primer cateto: ")
                val cateto1=readLine()!!.toDoubleOrNull();
                print("introduce el segundo cateto: ");
                val cateto2=readLine()!!.toDoubleOrNull();
                if(cateto1==null || cateto2==null) continue;

                if(cateto1>0 && cateto2>0){
                    val hipotenusa=calcularHipotenusa(cateto1, cateto2);
                    val area=(cateto1*cateto2)/2;
                    val perimetro= cateto1+cateto2+hipotenusa;
                    println("la hipotenusa es $hipotenusa");
                    println("El area es $area");
                    println("El perimetro es $perimetro");
                }else{
                    println("Uno o ambos catetos son <=0, no se puede calcular correctamente");
                    continue;
                }
                contador++;
                println("¿Quiere calcular otro triangulo?(s/n): ");
                continuar=readLine()!!.lowercase();
            }
            println("Fin del programa.")
        }
        fun calcularHipotenusa(num1: Double, num2: Double): Double {
            return sqrt(num1 * num1 + num2 * num2);
        }
    }
}