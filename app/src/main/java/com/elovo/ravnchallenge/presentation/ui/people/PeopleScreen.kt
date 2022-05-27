package com.elovo.ravnchallenge.presentation.ui.people

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.ui.people.components.PersonCell

@OptIn(ExperimentalPagingApi::class)
@Composable
fun PeopleScreen(
    viewModel: PeopleViewModel = hiltViewModel()
) {
    val people = viewModel.people.collectAsLazyPagingItems()

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
