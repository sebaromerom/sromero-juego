package com.example.routes

import com.example.model.Juego
import com.example.model.Consola
import com.example.repository.ConsolaRepository
import com.example.repository.JuegoRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.juegoRoutes() {
    route("/juegos") {

        // GET /juegos
        get {
            call.respond(JuegoRepository.findAll())
        }

        // GET /juegos/{id}
        get("{id}") {
            val id = call.parameters["id"]
            val juego = id?.let { JuegoRepository.findById(it) }
            if (juego != null) {
                call.respond(juego)
            } else {
                call.respondText("Juego no encontrado", status = HttpStatusCode.NotFound)
            }
        }

        // POST /juegos
        post {
            val body = call.receive<Juego>()
            val consola = ConsolaRepository.findById(body.consolaId)
            if (consola == null) {
                call.respondText("Consola no válida", status = HttpStatusCode.BadRequest)
                return@post
            }
            val nuevo = JuegoRepository.create(body.nombre, body.consolaId, body.anioLanzamiento)
            call.respond(HttpStatusCode.Created, nuevo)
        }

        // PUT /juegos
        put {
            val body = call.receive<Juego>()
            val actualizado = JuegoRepository.update(body.id, body.nombre, body.consolaId, body.anioLanzamiento)
            if (actualizado != null) {
                call.respond(actualizado)
            } else {
                call.respondText("Juego no encontrado", status = HttpStatusCode.NotFound)
            }
        }

        // DELETE /juegos/{id}
        delete("{id}") {
            val id = call.parameters["id"]
            if (id != null && JuegoRepository.deleteById(id)) {
                call.respondText("Juego eliminado")
            } else {
                call.respondText("Juego no encontrado", status = HttpStatusCode.NotFound)
            }
        }

        // Parte 2: Filtro por consola ID
        // GET /juegos/consolas/{consolaId}
        get("/consolas/{consolaId}") {
            val consolaId = call.parameters["consolaId"]
            if (consolaId != null) {
                val juegos = JuegoRepository.findByConsolaId(consolaId)
                call.respond(juegos)
            } else {
                call.respondText("ID de consola requerido", status = HttpStatusCode.BadRequest)
            }
        }

        // Parte 2: Filtro por nombre de consola
        // GET /juegos/consolas/nombre/{nombre}
        get("/consolas/nombre/{nombre}") {
            val nombre = call.parameters["nombre"]
            if (nombre != null) {
                val consola = ConsolaRepository.findAll().find {
                    it.nombre.equals(nombre, ignoreCase = true)
                }
                if (consola != null) {
                    val juegosFiltrados = JuegoRepository.findByConsolaId(consola.id)
                    call.respond(juegosFiltrados)
                } else {
                    call.respondText("Consola no encontrada", status = HttpStatusCode.NotFound)
                }
            } else {
                call.respondText("Nombre de consola requerido", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
