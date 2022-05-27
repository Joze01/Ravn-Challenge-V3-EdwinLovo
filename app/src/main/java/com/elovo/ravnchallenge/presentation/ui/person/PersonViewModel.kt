package com.elovo.ravnchallenge.presentation.ui.person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.elovo.domain.common.onError
import com.elovo.domain.common.onLoading
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.usecase.GetPersonUseCase
import com.elovo.domain.model.Person
import com.elovo.ravnchallenge.presentation.navigation.navgraph.PERSON_ID_ARG_KEY
import com.elovo.ravnchallenge.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val personId = savedStateHandle.get<String>(PERSON_ID_ARG_KEY) ?: ""
    var person by mutableStateOf<Person?>(null)

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnAddFavorite -> {
                // TODO: Implement method
            }
            is PersonEvent.OnRemoveFavorite -> {
                // TODO: Implement method
            }
        }
    }

    fun getPerson() {
        executeUseCase(checkConnection = false) {
            getPersonUseCase(personId).collect { result ->
                result.onLoading { isLoading = true }
                result.onSuccess {
                    person = it
                    isLoading = false
                }
                result.onError {
                }
            }
        }
    }
}
