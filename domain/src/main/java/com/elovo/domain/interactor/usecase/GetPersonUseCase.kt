package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface GetPersonUseCase {

    suspend operator fun invoke(personId: String): Flow<RavnResult<Person>>
}
