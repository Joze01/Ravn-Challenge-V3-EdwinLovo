package com.elovo.domain.interactor.usecase

import app.cash.turbine.test
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.person
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class UpdateFavoriteStatusUseCaseImplTest {
    private lateinit var personRepository: PersonRepository
    private lateinit var updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase

    @Test
    fun testUseCase() {
        runBlocking {
            personRepository = mock()
            Mockito.`when`(personRepository.updateFavoriteStatus("1", true)).thenReturn(
                flow {
                    emit(
                        RavnResult.Success(person.copy(isFavorite = true))
                    )
                }
            )
            updateFavoriteStatusUseCase = UpdateFavoriteStatusUseCaseImpl(personRepository)
            updateFavoriteStatusUseCase("1", true).test {
                val item = awaitItem()
                item.onSuccess {
                    Assert.assertEquals("1", it.id)
                    Assert.assertTrue(it.isFavorite == true)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
