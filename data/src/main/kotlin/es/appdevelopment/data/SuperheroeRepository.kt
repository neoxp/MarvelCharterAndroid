package es.appdevelopment.data

import es.appdevelopment.domain.Superheroe

interface SuperheroeRepository {
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe>
    suspend fun showSuperheroe(superheroeId: Int): Superheroe
}
