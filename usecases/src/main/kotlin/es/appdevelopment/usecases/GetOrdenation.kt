package es.appdevelopment.usecases

import es.appdevelopment.data.OrdenationRepository
import es.appdevelopment.domain.Ordenation

class GetOrdenation(
    private val ordenationRepository: OrdenationRepository
)
{
    operator fun invoke(): Ordenation = ordenationRepository.getOrdenation()
}