package com.elovo.ravnchallenge.presentation.ui.person

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun PersonScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    popBackStack: () -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: PersonViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getPerson()
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> popBackStack()
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(context.getString(event.message))
                }
                else -> Unit
            }
        }
    }

    BodyLayout(
        header = {
            RavnAppBar(
                title = viewModel.person?.name ?: "",
                goBack = { viewModel.goBack() }
            )
        }
    ) {
    }
}
