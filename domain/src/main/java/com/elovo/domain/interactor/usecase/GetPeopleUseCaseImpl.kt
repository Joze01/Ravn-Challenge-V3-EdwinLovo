package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.AllPeople
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetPeopleUseCase {

    override suspend fun invoke(endCursor: String): RavnResult<AllPeople> =
        personRepository.getAllPeople(endCursor)
}
