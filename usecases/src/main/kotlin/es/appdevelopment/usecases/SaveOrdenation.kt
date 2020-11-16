package es.appdevelopment.usecases

import es.appdevelopment.data.OrdenationRepository
import es.appdevelopment.domain.Ordenation

class SaveOrdenation(
    private val ordenationRepository: OrdenationRepository)
{
    operator fun invoke(ordenation: Ordenation) = ordenationRepository.saveOrdenation(ordenation)
}