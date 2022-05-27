package com.elovo.ravnchallenge.presentation.navigation.navgraph

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.elovo.ravnchallenge.presentation.ui.common.bottombar.BottomBar
import com.elovo.ravnchallenge.presentation.ui.favorites.FavoritesScreen
import com.elovo.ravnchallenge.presentation.ui.people.PeopleScreen

const val BOTTOM_BAR_ROUTE = "bottom_bar"

fun NavGraphBuilder.bottomBarNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    navigation(
        startDestination = BottomBar.People.route,
        route = BOTTOM_BAR_ROUTE
    ) {
        composable(route = BottomBar.People.route) {
            PeopleScreen(
                onNavigate = { uiEvent ->
                    navController.navigate(uiEvent.route) {
                        launchSingleTop = true
                    }
                },
                scaffoldState = scaffoldState
            )
        }
        composable(route = BottomBar.Favorites.route) {
            FavoritesScreen()
        }
    }
}
