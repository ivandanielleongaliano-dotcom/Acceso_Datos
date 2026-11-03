package practica2.Ejercicio5

import practica2.Ejercicio2.Notificable
import practica2.Ejercicio3.Pedido
import practica2.EstadoPedido
import practica2.ResourceState

class Cliente (id: Int, nombre: String, email:String): Usuario(id, nombre, email), Notificable {
    override fun mostrarDashboard(pedidos: List<Pedido>) {
        println("Panel de Cliente")
        println("Hola <$nombre>")
        println("Tienes <${pedidos.count{it.estadoPedido != EstadoPedido.ENTREGADO && it.idCliente == id}}> pedidos pendientes de entrega")
    }

    override fun enviarNotificacion(texto: String, estado: ResourceState) {
        println("Mensaje para el cliente <$nombre>")
        println("Mensaje: $texto")

        when (estado) {
            is ResourceState.Loading -> println("Cargando...")
            is ResourceState.Success -> {
                println("Id del Pedido:${estado.pedido.idPedido}")
                estado.pedido.listaProductos.forEach {
                    println(it)
                }
            }
            is ResourceState.Error -> println(estado.message)
        }
    }
}