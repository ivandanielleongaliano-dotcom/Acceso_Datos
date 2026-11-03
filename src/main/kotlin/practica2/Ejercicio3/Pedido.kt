package practica2.Ejercicio3

import practica2.Ejercicio2.Imprimible
import practica2.EstadoPedido
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class Pedido(val idPedido: Int, val listaProductos: List<Producto>, val estadoPedido:
EstadoPedido = EstadoPedido.PENDIENTE , val fecha: LocalDateTime, val idCliente: Int, val idRepartidor: Int): Imprimible{
    override fun imprimirTicket() {
        super.imprimirTicket()
        var total = 0.0
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        println("Pedido n:$idPedido")
        println("Cliente: $idCliente| Repartidor: $idRepartidor")
        println("Estado n:$estadoPedido")
        println("-".repeat(20))
        listaProductos.forEach {
            println(it)
            total += it.precio
        }
        println("-".repeat(20))
        println("Total: ${"%.2f".format(total)} €")
        println()
        println("${fecha.format(formato)}")
    }
}
