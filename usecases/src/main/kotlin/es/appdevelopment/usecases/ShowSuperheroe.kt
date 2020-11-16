package es.appdevelopment.usecases

import es.appdevelopment.data.SuperheroeRepository
import es.appdevelopment.domain.Superheroe

class ShowSuperheroe(
    private val superheroeRepository: SuperheroeRepository)
{
    suspend operator fun invoke(superheroeId: Int): Superheroe = superheroeRepository.showSuperheroe(superheroeId)
}