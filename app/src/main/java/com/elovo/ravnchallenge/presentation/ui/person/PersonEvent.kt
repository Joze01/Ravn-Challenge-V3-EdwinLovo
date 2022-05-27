package com.elovo.ravnchallenge.presentation.ui.person

sealed class PersonEvent {
    data class OnAddFavorite(val personId: String) : PersonEvent()
    data class OnRemoveFavorite(val personId: String) : PersonEvent()
}
