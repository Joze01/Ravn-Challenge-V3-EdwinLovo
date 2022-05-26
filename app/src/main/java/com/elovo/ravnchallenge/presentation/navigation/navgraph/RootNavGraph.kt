package com.elovo.ravnchallenge.presentation.navigation.navgraph

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navController,
        startDestination = BOTTOM_BAR_ROUTE
    ) {
        bottomBarNavGraph(navController = navController, scaffoldState = scaffoldState)
    }
}
