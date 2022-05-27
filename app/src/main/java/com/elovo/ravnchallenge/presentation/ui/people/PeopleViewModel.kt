package com.elovo.ravnchallenge.presentation.ui.people

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import com.elovo.ravnchallenge.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    var people = Pager(PagingConfig(pageSize = 5)) {
        PeopleSource(getPeopleUseCase)
    }.flow.cachedIn(viewModelScope)

    fun refresh(refreshData: () -> Unit) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            refreshData()
            _isRefreshing.emit(false)
        }
    }
}
