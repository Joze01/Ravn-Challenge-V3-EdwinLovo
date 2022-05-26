package com.elovo.domain.interactor.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    @ExperimentalPagingApi
    fun getPeople(): Flow<PagingData<Person>>
}
