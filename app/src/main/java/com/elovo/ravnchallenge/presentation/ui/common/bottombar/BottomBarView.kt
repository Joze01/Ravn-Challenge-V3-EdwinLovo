package com.elovo.ravnchallenge.presentation.ui.common.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.elovo.ravnchallenge.R

@Composable
fun BottomBarView(navController: NavHostController) {

    val screens = listOf(
        BottomBar.People,
        BottomBar.Favorites
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Column {
        BottomNavigation(elevation = 8.dp) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun RowScope.AddItem(
    screen: BottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = stringResource(id = screen.title), fontSize = 9.sp)
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.iconResource),
                contentDescription = stringResource(
                    id = R.string.cd_bottom_tab,
                    stringResource(id = screen.title)
                )
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route?.contains(screen.route) == true
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(BottomBar.People.route)
                launchSingleTop = true
                restoreState = true
            }
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}
