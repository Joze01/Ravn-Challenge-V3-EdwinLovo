package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetPersonUseCase {

    override suspend fun invoke(personId: String): Flow<RavnResult<Person>> =
        personRepository.getPerson(personId)
}
