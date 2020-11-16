package es.appdevelopment.marvelapi

import es.appdevelopment.data.OrdenationRepository
import es.appdevelopment.domain.Ordenation
import es.appdevelopment.usecases.SaveOrdenation
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OrdenationTest {

    @Test
    fun `when execute SaveOrdenation, call ordenationRepository`() {
        val ordenationRepository = mockk<OrdenationRepository>(relaxed = true)
        val saveOrdenation = SaveOrdenation(ordenationRepository)

        saveOrdenation(Ordenation.NAME)

        verify { ordenationRepository.saveOrdenation(Ordenation.NAME) }
    }
}