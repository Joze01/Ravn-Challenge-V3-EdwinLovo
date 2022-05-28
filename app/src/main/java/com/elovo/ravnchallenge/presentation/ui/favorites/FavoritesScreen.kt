package com.elovo.ravnchallenge.presentation.ui.favorites

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.navigation.Screen
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.ui.people.components.PersonCell
import com.elovo.ravnchallenge.presentation.ui.theme.MediumPadding
import com.elovo.ravnchallenge.presentation.ui.theme.RavnTypography
import com.elovo.ravnchallenge.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun FavoritesScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getFavorites()
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    BodyLayout(
        header = {
            RavnAppBar(title = stringResource(id = R.string.title_favorites))
        },
        hasPadding = false
    ) {
        LazyColumn {
            items(viewModel.favorites) {
                PersonCell(
                    person = it,
                    onClick = { personId ->
                        viewModel.sendUiEvent(
                            UiEvent.Navigate("${Screen.PersonScreen.route}/$personId")
                        )
                    }
                )
            }
        }
        if (viewModel.favorites.isEmpty()) {
            Text(
                text = stringResource(id = R.string.favorites_empty),
                style = RavnTypography.h2LowEmphasis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MediumPadding)
            )
        }
    }
}
