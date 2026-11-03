package practica2.Ejercicio5

import practica2.Ejercicio3.Pedido

abstract class Usuario(val id: Int, val nombre: String, val email: String){

    abstract fun mostrarDashboard(pedidos: List<Pedido>)
}