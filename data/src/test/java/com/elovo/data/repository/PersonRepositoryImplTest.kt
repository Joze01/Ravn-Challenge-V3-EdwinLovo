package com.elovo.data.repository

import app.cash.turbine.test
import com.elovo.data.db.dao.PersonDao
import com.elovo.data.personApolloModel
import com.elovo.data.remote.datasource.FakePeopleRemoteImpl
import com.elovo.data.util.mapper.mapToModel
import com.elovo.domain.common.DataSourceException
import com.elovo.domain.common.onError
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.repository.PersonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class PersonRepositoryImplTest {
    private lateinit var fakePeopleRemote: FakePeopleRemoteImpl
    private lateinit var personDao: PersonDao

    private lateinit var personRepository: PersonRepository

    @Before
    fun setUp() {
        fakePeopleRemote = FakePeopleRemoteImpl()
        personDao = mock {
            on { getFavorites() } doReturn flow {
                emit(listOf(personApolloModel.mapToModel().mapToRoomEntity()))
            }
        }
        personRepository = PersonRepositoryImpl(fakePeopleRemote, personDao)
    }

    @Test
    fun test_getAllPeople() {
        runTest {
            personRepository.getAllPeople("").onSuccess {
                Assert.assertEquals(it.people.size, 3)
                Assert.assertTrue(it.pageInfo.hasNextPage)
                Assert.assertFalse(it.pageInfo.hasPreviousPage)
            }
        }
    }

    @Test
    fun test_GetPerson_search_for_invalid_person_id() {
        runTest {
            personRepository.getPerson("").test {
                val item = awaitItem()
                item.onError {
                    Assert.assertTrue(it is DataSourceException.Unexpected)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun test_GetPerson_search_for_valid_person_id() {
        runTest {
            personRepository.getPerson("1").test {
                val item = awaitItem()
                item.onSuccess {
                    Assert.assertEquals(it.name, "Yoda")
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun test_getAllFavorites() {
        runTest {
            personRepository.getAllFavorites().test {
                val item = awaitItem()
                item.onSuccess {
                    Assert.assertEquals(it.size, 1)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    /*@Test
    fun test_updateFavoriteStatus_for_invalid_id() {
        runTest {
            personDao.insertPerson(personApolloModel.mapToModel().mapToRoomEntity())
            personRepository.updateFavoriteStatus("1", true).test {
                val item = awaitItem()
                item.onError {
                    Assert.assertTrue(it is DataSourceException.Unexpected)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }
    }*/
}
