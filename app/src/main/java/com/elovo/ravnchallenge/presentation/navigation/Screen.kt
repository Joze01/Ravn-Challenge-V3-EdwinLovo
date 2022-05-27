package com.elovo.ravnchallenge.presentation.navigation

sealed class Screen(val route: String) {
    object PersonScreen : Screen("person")
}
