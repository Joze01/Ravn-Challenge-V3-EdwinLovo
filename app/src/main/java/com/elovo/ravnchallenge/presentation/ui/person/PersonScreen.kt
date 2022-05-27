package com.elovo.ravnchallenge.presentation.ui.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.DataCell
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.ui.common.SectionHeader
import com.elovo.ravnchallenge.presentation.utils.UiEvent
import com.elovo.ravnchallenge.ui.theme.MediumPadding
import com.elovo.ravnchallenge.ui.theme.RavnTypography
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
        },
        hasPadding = false
    ) {
        SectionHeader(message = stringResource(id = R.string.person_general_info))
        Column(modifier = Modifier.padding(start = MediumPadding)) {
            DataCell(
                label = stringResource(id = R.string.person_eye_color),
                value = viewModel.person?.eyeColor
            )
            DataCell(
                label = stringResource(id = R.string.person_hair_color),
                value = viewModel.person?.hairColor
            )
            DataCell(
                label = stringResource(id = R.string.person_skin_color),
                value = viewModel.person?.skinColor
            )
            DataCell(
                label = stringResource(id = R.string.person_birth_year),
                value = viewModel.person?.birthYear
            )
        }
        SectionHeader(message = stringResource(id = R.string.person_vehicles))
        Column(modifier = Modifier.padding(start = MediumPadding)) {
            viewModel.person?.vehicleConnection?.forEach { vehicle ->
                vehicle?.let {
                    DataCell(
                        label = it.name ?: ""
                    )
                }
            }
            if (viewModel.person?.vehicleConnection?.isEmpty() == true) {
                Text(
                    text = stringResource(id = R.string.person_no_vehicles),
                    style = RavnTypography.h2LowEmphasis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
