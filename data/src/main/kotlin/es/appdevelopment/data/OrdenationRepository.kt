package es.appdevelopment.data

import es.appdevelopment.domain.Ordenation

interface OrdenationRepository {
    fun saveOrdenation(ordenation: Ordenation)
    fun getOrdenation() : Ordenation
}