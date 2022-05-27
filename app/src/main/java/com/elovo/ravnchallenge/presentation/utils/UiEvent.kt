package com.elovo.ravnchallenge.presentation.utils

import androidx.annotation.StringRes

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class PopAndNavigate(val route: String, val popToRoute: String) : UiEvent()
    data class ShowSnackBar(
        @StringRes val message: Int,
        @StringRes val action: Int? = null
    ) : UiEvent()
}
