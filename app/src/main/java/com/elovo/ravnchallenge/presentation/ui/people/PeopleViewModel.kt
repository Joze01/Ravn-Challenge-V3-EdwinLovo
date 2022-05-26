package com.elovo.ravnchallenge.presentation.ui.people

import androidx.lifecycle.ViewModel
import com.elovo.domain.interactor.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel()
