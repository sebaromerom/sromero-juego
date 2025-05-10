// En com/example/repository/JuegoRepository.kt
package com.example.repository

import com.example.model.Juego
import java.util.UUID

object JuegoRepository {
    private val juegos = mutableListOf<Juego>()

    init {
        // Datos de ejemplo
        // Asegúrate que los consolaId correspondan a IDs de tu ConsolaRepository
        juegos.add(Juego(id = UUID.randomUUID().toString(), nombre = "Spider-Man 2", consolaId = "ps5", anioLanzamiento = 2023))
        juegos.add(Juego(id = UUID.randomUUID().toString(), nombre = "Halo Infinite", consolaId = "xsx", anioLanzamiento = 2021))
        juegos.add(Juego(id = UUID.randomUUID().toString(), nombre = "Zelda: Tears of the Kingdom", consolaId = "switch", anioLanzamiento = 2023))
        juegos.add(Juego(id = UUID.randomUUID().toString(), nombre = "God of War Ragnarök", consolaId = "ps5", anioLanzamiento = 2022))

    }

    fun create(nombre: String, consolaId: String, anioLanzamiento: Int): Juego {
        // Validar que consolaId exista podría ser una buena adición
        // if (ConsolaRepository.findById(consolaId) == null) throw IllegalArgumentException("Consola ID no válida")
        val nuevoJuego = Juego(id = UUID.randomUUID().toString(), nombre = nombre, consolaId = consolaId, anioLanzamiento = anioLanzamiento)
        juegos.add(nuevoJuego)
        return nuevoJuego
    }

    fun findAll(): List<Juego> = juegos.toList()

    fun findById(id: String): Juego? = juegos.find { it.id == id }

    fun update(id: String, nombre: String, consolaId: String, anioLanzamiento: Int): Juego? {
        val juego = findById(id)
        juego?.let {
            // if (ConsolaRepository.findById(consolaId) == null) throw IllegalArgumentException("Consola ID no válida al actualizar")
            it.nombre = nombre
            it.consolaId = consolaId
            it.anioLanzamiento = anioLanzamiento
        }
        return juego
    }

    fun deleteById(id: String): Boolean = juegos.removeIf { it.id == id }

    fun deleteAll(): Boolean {
        val hadElements = juegos.isNotEmpty()
        juegos.clear()
        return hadElements
    }

    // Parte 2: Filtrar juegos por consolaId
    fun findByConsolaId(consolaId: String): List<Juego> {
        return juegos.filter { it.consolaId.equals(consolaId, ignoreCase = true) }
    }
}