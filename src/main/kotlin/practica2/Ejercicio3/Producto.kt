package practica2.Ejercicio3

data class Producto(val id: Int, val nombre: String, var precio: Double){
    override fun toString(): String {
        return "$nombre $precio€"
    }
}
