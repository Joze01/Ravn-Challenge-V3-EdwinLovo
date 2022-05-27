package com.elovo.ravnchallenge.presentation.ui.people

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.ui.people.components.PersonCell
import com.elovo.ravnchallenge.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalPagingApi::class)
@Composable
fun PeopleScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: PeopleViewModel = hiltViewModel()
) {
    val people = viewModel.people.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(context.getString(event.message))
                }
                else -> Unit
            }
        }
    }

    BodyLayout(
        header = {
            RavnAppBar(title = stringResource(id = R.string.title_people))
        }, hasPadding = false
    ) {
        LazyColumn {
            items(people) { person ->
                person?.let {
                    PersonCell(
                        person = it,
                        onClick = { }
                    )
                }
            }
        }
    }
}
