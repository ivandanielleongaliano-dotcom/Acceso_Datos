package practicaIntroduccion
import kotlin.random.Random

fun main(){
    val temperaturas=generarLista();
    val media=calcularMedia(temperaturas);
    val tempCorregida=temperaturas.map {it ?: 0.0};
    println(temperaturas);
    println(media);
    println(tempCorregida);
    mostrarTemperaturas(tempCorregida);
}

fun generarLista(): MutableList<Double?>{
    var temperaturas: MutableList<Double?> = mutableListOf();
    do {
        temperaturas=buildList<Double?> {
            for(i in 1..20){
                if(Random.nextInt(1,6)==1){
                    add(null);
                }else {
                    add(Random.nextDouble(-20.0, 40.0));
                }
            }
        }.toMutableList();
        val comNull=temperaturas.count { it == null } >=4;
        val comPos=temperaturas.count { it != null && it >=0}>=4;
        val comNeg=temperaturas.count { it != null && it <0}>=4;
    }while(!(comNull && comPos && comNeg));
    return temperaturas;
}

fun calcularMedia(temps: List<Double?>): Double {
    val Lista=temps.filterNotNull();
    return Lista.average();
}

fun mostrarTemperaturas(temps: List<Double>){
    temps.forEach {
        when {
            it < 0 -> println("Temperatura Helada: $it")
            it >=0 && it <=30 -> println("Temperatura Normal: $it")
            else -> println("Ola de calor: $it")

        }
    }
}
