package es.appdevelopment.usecases

import es.appdevelopment.data.SuperheroeRepository
import es.appdevelopment.domain.Ordenation
import es.appdevelopment.domain.Superheroe

class ListSuperheroes (
    private val superheroeRepository: SuperheroeRepository)
{
     suspend operator fun invoke(offset: Int, limit: Int, orderBy: String): List<Superheroe> = superheroeRepository.listSuperheroes(offset, limit, orderBy)
}