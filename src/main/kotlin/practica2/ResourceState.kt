package practica2

import practica2.Ejercicio3.Pedido

sealed class ResourceState {
    object Loading : ResourceState()
    data class Success(val pedido: Pedido) : ResourceState()
    data class Error(val message: String) : ResourceState()
}
