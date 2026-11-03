package practica2

import practica2.Ejercicio3.Pedido
import practica2.Ejercicio3.Producto
import practica2.Ejercicio5.Cliente
import practica2.Ejercicio5.Repartidor
import practica2.Ejercicio5.Usuario
import java.time.LocalDateTime

fun main() {
    // 6A
    val clientes = listOf(
        Cliente(1, "Ana García", "ana@email.com"),
        Cliente(2, "Carlos López", "carlos@email.com")
    )

    val repartidores = listOf(
        Repartidor(101, "Pedro Picapiedra", "pedro@email.com", "1234 ABC"), // Válida
        Repartidor(102, "Marta Mármol", "marta@email.com", "")             // Vacía (activará init -> DESCONOCIDA)
    )

    val productosDisponibles = listOf(
        Producto(1, "Lentejas con chorizo", 6.50),
        Producto(2, "Barra pan", 0.70),
        Producto(3, "Botella agua 0,5L", 0.70),
        Producto(4, "Pieza de fruta", 1.50),
        Producto(5, "Yogur natural", 1.20)
    )

    val pedidos = mutableListOf(
        Pedido(
            idPedido = 1,
            listaProductos = listOf(productosDisponibles[0], productosDisponibles[1], productosDisponibles[2]),
            estadoPedido = EstadoPedido.PENDIENTE,
            fecha = LocalDateTime.now(),
            idCliente = 1,
            idRepartidor = 101
        )
    )

    val usuarios: List<Usuario> = clientes + repartidores

    // 6B
    var opcion: Int
    do {
        println("\n--- MENÚ APP REPARTO A DOMICILIO ---")
        println("1. Mostrar dashboards de usuarios")
        println("2. Enviar notificaciones")
        println("3. Imprimir tickets")
        println("4. Avanzar estado del pedido")
        println("5. Simular respuesta de red")
        println("6. Salir")
        print("Elige una opción: ")

        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("\n[1] MOSTRANDO DASHBOARDS:")
                for (usuario in usuarios) {
                    usuario.mostrarDashboard(pedidos)
                    println("-----------------------------------")
                }
            }
            2 -> {
                println("\n[2] ENVIAR NOTIFICACIÓN:")
                print("Introduce el ID del cliente: ")
                val idCliente = readLine()?.toIntOrNull()
                val cliente = clientes.find { it.id == idCliente }

                if (cliente != null) {
                    print("Introduce el texto de la notificación: ")
                    val texto = readLine() ?: ""
                    // Por defecto mandamos un estado Success por defecto para una notificación normal
                    cliente.enviarNotificacion(texto, ResourceState.Success(pedidos.first()))
                } else {
                    println("Error: Cliente no encontrado.")
                }
            }
            3 -> {
                println("\n[3] IMPRIMIR TICKETS:")
                print("Introduce el ID del cliente: ")
                val idCliente = readLine()?.toIntOrNull()
                val cliente = clientes.find { it.id == idCliente }

                if (cliente != null) {
                    val pedidosCliente = pedidos.filter { it.idCliente == cliente.id }
                    if (pedidosCliente.isEmpty()) {
                        println("El cliente no tiene pedidos registrados.")
                    } else {
                        for (p in pedidosCliente) {
                            p.imprimirTicket()
                            println("===================================")
                        }
                    }
                } else {
                    println("Error: Cliente no encontrado.")
                }
            }
            4 -> {
                println("\n[4] AVANZAR ESTADO DEL PEDIDO:")
                print("Introduce el número de pedido: ")
                val idBuscado = readLine()?.toIntOrNull()
                val indicePedido = pedidos.indexOfFirst { it.idPedido == idBuscado }

                if (indicePedido != -1) {
                    val pedidoActual = pedidos[indicePedido]
                    println("Estado actual: ${pedidoActual.estadoPedido} | Tiempo estimado: ${pedidoActual.estadoPedido.tiempo} min")

                    val siguienteEstado = when (pedidoActual.estadoPedido) {
                        EstadoPedido.PENDIENTE -> EstadoPedido.EN_PREPARACION
                        EstadoPedido.EN_PREPARACION -> EstadoPedido.EN_REPARTO
                        EstadoPedido.EN_REPARTO -> EstadoPedido.ENTREGADO
                        EstadoPedido.ENTREGADO -> null
                    }

                    if (siguienteEstado != null) {
                        // Generar nueva versión inmutable usando .copy()
                        val pedidoModificado = pedidoActual.copy(estadoPedido = siguienteEstado)
                        pedidos[indicePedido] = pedidoModificado
                        println("¡Estado avanzado con éxito!")
                        println("Nuevo estado: ${pedidoModificado.estadoPedido} | Nuevo tiempo estimado: ${pedidoModificado.estadoPedido.tiempo} min")
                    } else {
                        println("El pedido ya está en estado ENTREGADO y ya no se puede avanzar más.")
                    }
                } else {
                    println("Error: El pedido con ID $idBuscado no existe.")
                }
            }
            5 -> {
                println("\n[5] SIMULAR RESPUESTA DE RED:")
                print("Introduce el ID del cliente: ")
                val idCliente = readLine()?.toIntOrNull()
                val cliente = clientes.find { it.id == idCliente }

                if (cliente != null) {
                    println("Selecciona el estado de red a simular:")
                    println("1. Loading")
                    println("2. Success")
                    println("3. Error")
                    print("Opción (1-3): ")
                    val opcionRed = readLine()?.toIntOrNull()

                    val resourceState: ResourceState = when (opcionRed) {
                        1 -> ResourceState.Loading
                        2 -> {
                            val pedidoReferencia = pedidos.firstOrNull() ?: Pedido(0, emptyList(), EstadoPedido.PENDIENTE, LocalDateTime.now(), cliente.id, 101)
                            ResourceState.Success(pedidoReferencia)
                        }
                        3 -> ResourceState.Error("Fallo de conexión con el servidor.")
                        else -> {
                            println("Opción no válida, se asignará Error por defecto.")
                            ResourceState.Error("Opción de red incorrecta.")
                        }
                    }

                    print("Introduce el texto descriptivo de la notificación: ")
                    val mensaje = readLine() ?: ""
                    cliente.enviarNotificacion(mensaje, resourceState)
                } else {
                    println("Error: Cliente no encontrado.")
                }
            }
            6 -> {
                println("Saliendo de la aplicación... ¡Hasta pronto!")
            }
            else -> {
                println("Opción no válida. Inténtalo de nuevo.")
            }
        }
    } while (opcion != 6)
}