package com.elovo.domain.interactor.usecase

import com.elovo.domain.allPeople
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.people
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
internal class GetPeopleUseCaseImplTest {
    private lateinit var personRepository: PersonRepository
    private lateinit var getPeopleUseCase: GetPeopleUseCase

    @Test
    fun testUseCase() {
        runBlocking {
            personRepository = mock()
            `when`(personRepository.getAllPeople("123")).thenReturn(
                RavnResult.Success(allPeople)
            )
            getPeopleUseCase = GetPeopleUseCaseImpl(personRepository)
            val result = getPeopleUseCase("123")
            result.onSuccess {
                Assert.assertTrue(it.pageInfo.hasNextPage)
                Assert.assertEquals(it.people.size, people.size)
            }
        }
    }
}
