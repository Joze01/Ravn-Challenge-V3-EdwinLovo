package com.elovo.ravnchallenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.elovo.ravnchallenge.presentation.navigation.navgraph.Navigation
import com.elovo.ravnchallenge.presentation.ui.common.bottombar.BottomBarView
import com.elovo.ravnchallenge.presentation.ui.theme.RavnChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            RavnChallengeTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomBarView(navController = navController)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController = navController, scaffoldState = scaffoldState)
                    }
                }
            }
        }
    }
}
