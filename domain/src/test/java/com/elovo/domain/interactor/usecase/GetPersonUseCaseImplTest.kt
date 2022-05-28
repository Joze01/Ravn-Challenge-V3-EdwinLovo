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
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetPersonUseCaseImplTest {
    private lateinit var personRepository: PersonRepository
    private lateinit var getPersonUseCase: GetPersonUseCase

    @Test
    fun testUseCase() {
        runBlocking {
            personRepository = mock()
            `when`(personRepository.getPerson("1")).thenReturn(
                flow { emit(RavnResult.Success(person)) }
            )
            getPersonUseCase = GetPersonUseCaseImpl(personRepository)
            getPersonUseCase("1").test {
                val item = awaitItem()
                item.onSuccess {
                    Assert.assertEquals("1", it.id)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
