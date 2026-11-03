package practicaIntroduccion

import kotlin.random.Random

fun main(){
    val precios = List(10) { Random.nextDouble(1.0, 100.0) }
    println("Envio: ${calcularTotalPedido(precios, cuponDescuento = "Descuento10")}")
    println("Envio: ${calcularTotalPedido(precios, 4.99)}")
}

fun calcularTotalPedido(preciosProductos:List<Double>, gastosEnvio:Double= 2.99, cuponDescuento:String?=null) : Double{
    var envio=gastosEnvio
    var sumatoria=preciosProductos.sum()
    when {
        cuponDescuento.equals("DESCUENTO10", true)-> sumatoria*=0.90
        cuponDescuento.equals("VIP", true)->{
            sumatoria*=0.80
            envio=0.0
        }
        else-> sumatoria=sumatoria
    }
    if(sumatoria>50){
        return sumatoria
    }else{
        return sumatoria+envio
    }
}

