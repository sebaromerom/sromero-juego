// En main/kot/ConsolaRepository.kt
package com.example.repository

import com.example.model.Consola
import java.util.UUID

object ConsolaRepository {
    private val consolas = mutableListOf<Consola>()

    // Datos de ejemplo iniciales
    init {
        consolas.add(Consola(id = "ps5", nombre = "PlayStation 5", fabricante = "Sony"))
        consolas.add(Consola(id = "xsx", nombre = "Xbox Series X", fabricante = "Microsoft"))
        consolas.add(Consola(id = "switch", nombre = "Nintendo Switch", fabricante = "Nintendo"))
        // Puedes añadir más, o generar IDs con UUID si las creas dinámicamente
    }

    fun create(nombre: String, fabricante: String): Consola {
        val nuevaConsola = Consola(id = UUID.randomUUID().toString(), nombre = nombre, fabricante = fabricante)
        consolas.add(nuevaConsola)
        return nuevaConsola
    }

    fun findAll(): List<Consola> = consolas.toList()

    fun findById(id: String): Consola? = consolas.find { it.id.equals(id, ignoreCase = true) } // Búsqueda flexible por ID

    // Podrías añadir update y delete si es necesario para el ejercicio
}