package com.elovo.ravnchallenge.presentation.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.utils.Connectivity
import com.elovo.ravnchallenge.presentation.utils.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
    var errorOccurred by mutableStateOf(false)

    @Inject
    protected lateinit var connectivity: Connectivity

    /**
     * Use this val to store one time events defined in UiEvent Class
     **/
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /**
     * Use this val to check internet connection
     **/
    val hasInternet: Boolean
        get() = connectivity.hasNetworkAccess()

    /**
     * Use this function to trigger one time events defined in UiEvent Class
     **/
    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun goBack() {
        sendUiEvent(UiEvent.PopBackStack)
    }

    /**
     * Use this function to call use cases in a coroutine in the viewModel
     * action: is the use case you want to call
     **/
    inline fun executeUseCase(
        crossinline action: suspend () -> Unit,
        crossinline noInternetAction: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (hasInternet) {
                action()
            } else {
                noInternetAction()
            }
        }
    }

    inline fun executeUseCase(
        checkConnection: Boolean = true,
        crossinline action: suspend () -> Unit
    ) {
        if (checkConnection) {
            if (hasInternet) {
                viewModelScope.launch(Dispatchers.IO) {
                    action()
                }
            } else {
                isLoading = false
                sendUiEvent(
                    UiEvent.ShowSnackBar(R.string.common_no_internet)
                )
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                action()
            }
        }
    }

    fun showInformativeSnackBar(@StringRes messageId: Int) {
        sendUiEvent(UiEvent.ShowSnackBar(messageId))
    }
}
