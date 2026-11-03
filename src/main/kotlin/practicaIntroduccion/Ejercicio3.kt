package practicaIntroduccion

var inventario: MutableMap<String, Pair<Int, Double>> = mutableMapOf();
fun main(){
    inventario.put("cascos jbl", Pair(3,39.99))
    inventario.put("raton msi", Pair(5,29.99))
    inventario.put("teclado msi", Pair(2,59.99))
    inventario.put("monitor lg", Pair(7,89.99))

    println("importe=${procesarVenta("Raton MSI", 4)}")
    generarInformeStock()
}

fun procesarVenta(producto:String, cantidadVendida:Int): Double{
    var importeDevuelto=-1.0
    if(!inventario.containsKey(producto.lowercase())){
        println("No existe este producto")
        return importeDevuelto
    }else{
        val par= inventario[producto.lowercase()]!!
        if(par.first>=cantidadVendida){
            inventario[producto.lowercase()] = Pair(par.first-cantidadVendida,par.second)
            importeDevuelto=cantidadVendida*par.second
            return importeDevuelto
        }else if(par.first<cantidadVendida){
            importeDevuelto=par.first*par.second
            inventario[producto.lowercase()] = Pair(0,par.second)
            return importeDevuelto
        }else{
            return importeDevuelto
        }
    }
}

fun generarInformeStock() {
    inventario.forEach { (nombre, par) ->
        if (par.first <= 3) println("STOCK BAJO: Producto:$nombre, Stock:${par.first}")
    }
}