package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Consola(
    val id: String, // Puede ser un UUID o un ID corto como "ps5", "switch"
    val nombre: String,
    val fabricante: String
    // Puedes añadir más atributos, como anioLanzamientoConsola, etc.
)