package com.elovo.ravnchallenge.presentation.ui.people

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar

@Composable
fun PeopleScreen() {

    BodyLayout(
        header = {
            RavnAppBar(title = stringResource(id = R.string.title_people))
        }
    ) {
    }
}
