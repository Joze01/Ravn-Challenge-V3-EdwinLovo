package com.elovo.domain.interactor.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetPeopleUseCase {

    @ExperimentalPagingApi
    override fun invoke(): Flow<PagingData<Person>> = personRepository.getPeople()
}
