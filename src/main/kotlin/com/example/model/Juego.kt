package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Juego(
    val id: String, // UUID, por ejemplo
    var nombre: String,
    var consolaId: String, // ID de la Consola a la que pertenece/para la que está disponible
    var anioLanzamiento: Int
    // Otros atributos del juego...
)