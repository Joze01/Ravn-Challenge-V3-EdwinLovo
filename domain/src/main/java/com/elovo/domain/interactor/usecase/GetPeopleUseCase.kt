package com.elovo.domain.interactor.usecase

import com.elovo.domain.common.RavnResult
import com.elovo.domain.model.AllPeople

interface GetPeopleUseCase {

    suspend operator fun invoke(endCursor: String): RavnResult<AllPeople>
}
