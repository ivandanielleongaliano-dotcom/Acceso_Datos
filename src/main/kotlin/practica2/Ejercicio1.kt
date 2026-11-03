package practica2

enum class EstadoPedido (val tiempo: Int){
    PENDIENTE(20),
    EN_PREPARACION(10),
    EN_REPARTO(5),
    ENTREGADO(0)
}