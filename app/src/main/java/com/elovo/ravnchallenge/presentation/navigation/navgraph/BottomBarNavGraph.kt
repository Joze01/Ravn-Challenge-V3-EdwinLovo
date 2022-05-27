package com.elovo.ravnchallenge.presentation.navigation.navgraph

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.elovo.ravnchallenge.presentation.navigation.Screen
import com.elovo.ravnchallenge.presentation.ui.common.bottombar.BottomBar
import com.elovo.ravnchallenge.presentation.ui.favorites.FavoritesScreen
import com.elovo.ravnchallenge.presentation.ui.people.PeopleScreen
import com.elovo.ravnchallenge.presentation.ui.person.PersonScreen

const val BOTTOM_BAR_ROUTE = "bottom_bar"
const val PERSON_ID_ARG_KEY = "person_id"

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
        composable(
            route = "${Screen.PersonScreen.route}/{$PERSON_ID_ARG_KEY}",
            arguments = listOf(
                navArgument(name = PERSON_ID_ARG_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            PersonScreen(
                onNavigate = { uiEvent ->
                    navController.navigate(uiEvent.route) {
                        launchSingleTop = true
                    }
                },
                popBackStack = { navController.popBackStack() },
                scaffoldState = scaffoldState
            )
        }
        composable(route = BottomBar.Favorites.route) {
            FavoritesScreen(
                onNavigate = { uiEvent ->
                    navController.navigate(uiEvent.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
