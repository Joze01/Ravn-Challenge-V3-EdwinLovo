package com.elovo.ravnchallenge.presentation.ui.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elovo.domain.common.onError
import com.elovo.domain.common.onLoading
import com.elovo.domain.common.onSuccess
import com.elovo.domain.interactor.usecase.GetFavoritesUseCase
import com.elovo.domain.model.Person
import com.elovo.ravnchallenge.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : BaseViewModel() {

    var favorites by mutableStateOf<List<Person>>(emptyList())

    fun getFavorites() {
        executeUseCase(checkConnection = false) {
            getFavoritesUseCase().collect { result ->
                result.onLoading { isLoading = true }
                result.onSuccess {
                    favorites = it
                    isLoading = false
                }
                result.onError {
                    favorites = emptyList()
                }
            }
        }
    }
}
