package com.elovo.domain.interactor.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface GetPeopleUseCase {

    @ExperimentalPagingApi
    operator fun invoke(): Flow<PagingData<Person>>
}
