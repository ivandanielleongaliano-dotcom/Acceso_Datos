package practica2.Ejercicio5

import practica2.Ejercicio3.Pedido
import practica2.EstadoPedido

class Repartidor(id: Int, nombre: String, email:String, var matricula: String): Usuario(id, nombre, email) {
    init {
        if (matricula.isBlank()) {
            matricula = "DESCONOCIDA"
            println("matricula en blanco")
        }else if (!matricula.matches(Regex("\\d{4} [A-Z]{3}"))) {
            matricula = "ERRONEA"
            println("matricula erronea")
        }
    }
    override fun mostrarDashboard(pedidos: List<Pedido>) {
        println("Panel de Repartidor")
        println("Conductor: <$nombre> | Vehículo: <$matricula>")
        println("Entregas asignadas: <${pedidos.count{it.estadoPedido == EstadoPedido.EN_REPARTO && it.idRepartidor == id}}>")
    }
}