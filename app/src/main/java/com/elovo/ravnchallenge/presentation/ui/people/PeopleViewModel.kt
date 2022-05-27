package com.elovo.ravnchallenge.presentation.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {

    val people = Pager(PagingConfig(pageSize = 5)) {
        PeopleSource(getPeopleUseCase)
    }.flow.cachedIn(viewModelScope)
}
