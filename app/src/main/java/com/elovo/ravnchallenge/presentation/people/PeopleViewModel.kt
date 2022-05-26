package com.elovo.ravnchallenge.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import com.elovo.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {

    @ExperimentalPagingApi
    val people: Flow<PagingData<Person>> =
        getPeopleUseCase().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
}
