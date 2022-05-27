package com.elovo.ravnchallenge.presentation.ui.person

sealed class PersonEvent {
    data class OnUpdateFavoriteStatus(val isFavorite: Boolean) : PersonEvent()
}
