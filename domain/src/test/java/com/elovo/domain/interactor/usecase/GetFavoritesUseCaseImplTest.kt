package com.elovo.domain.interactor.usecase

import app.cash.turbine.test
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.people
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetFavoritesUseCaseImplTest {
    private lateinit var personRepository: PersonRepository
    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Test
    fun testUseCase() {
        runBlocking {
            personRepository = mock()
            `when`(personRepository.getAllFavorites()).thenReturn(
                flow { emit(RavnResult.Success(people)) }
            )
            getFavoritesUseCase = GetFavoritesUseCaseImpl(personRepository)
            getFavoritesUseCase().test {
                val item = awaitItem()
                item.onSuccess {
                    Assert.assertEquals(it.size, people.size)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
