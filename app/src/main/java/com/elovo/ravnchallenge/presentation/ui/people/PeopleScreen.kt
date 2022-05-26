package com.elovo.ravnchallenge.presentation.ui.people

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar

@OptIn(ExperimentalPagingApi::class)
@Composable
fun PeopleScreen(
    viewModel: PeopleViewModel = hiltViewModel()
) {

    BodyLayout(
        header = {
            RavnAppBar(title = stringResource(id = R.string.title_people))
        }
    ) {
        LazyColumn {
            /*items(people) {
                Text(text = it?.name ?: "Error", style = RavnTypography.h2Default)
                Spacer(Modifier.height(24.dp))
            }*/
        }
    }
}
