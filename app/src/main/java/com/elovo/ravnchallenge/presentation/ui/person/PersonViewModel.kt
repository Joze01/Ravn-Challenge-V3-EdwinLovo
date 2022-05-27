package com.elovo.ravnchallenge.presentation.ui.person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.elovo.domain.model.Person
import com.elovo.ravnchallenge.presentation.navigation.navgraph.PERSON_ID_ARG_KEY
import com.elovo.ravnchallenge.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
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
}
