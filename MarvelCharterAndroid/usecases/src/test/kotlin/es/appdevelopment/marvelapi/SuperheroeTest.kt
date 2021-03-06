package es.appdevelopment.marvelapi

import es.appdevelopment.data.SuperheroeRepository
import es.appdevelopment.usecases.ListSuperheroes
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test


class SuperheroeTest {

    @Test
    fun `when execute ListSuperheroes, call superheroe Repository`() = runBlocking {
        val superheroeRepository = mockk<SuperheroeRepository>(relaxed = true)
        val listSuperheroes = ListSuperheroes(superheroeRepository)

        listSuperheroes(0, 50, "name")

        coVerify { superheroeRepository.listSuperheroes(0, 50, "name") }
    }
}
