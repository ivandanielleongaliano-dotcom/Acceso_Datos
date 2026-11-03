package practica2.Ejercicio2

import practica2.ResourceState

interface Notificable {
    fun enviarNotificacion(texto: String, estado: ResourceState)
}