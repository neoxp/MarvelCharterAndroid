package es.appdevelopment.marvelapi.framework

import es.appdevelopment.data.OrdenationRepository
import es.appdevelopment.domain.Ordenation
import es.appdevelopment.marvelapi.MarvelApp

class OrdenationDataSource : OrdenationRepository {

    override  fun saveOrdenation(ordenation: Ordenation) {
        MarvelApp.prefs.order = ordenation.typeOrdenation
    }

    override fun getOrdenation() : Ordenation = Ordenation.getOrdenationByType(MarvelApp.prefs.order)
}