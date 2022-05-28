package com.elovo.ravnchallenge.presentation.ui.person

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.BodyLayout
import com.elovo.ravnchallenge.presentation.ui.common.DataCell
import com.elovo.ravnchallenge.presentation.ui.common.LoadingCell
import com.elovo.ravnchallenge.presentation.ui.common.NoticeCell
import com.elovo.ravnchallenge.presentation.ui.common.RavnAppBar
import com.elovo.ravnchallenge.presentation.ui.common.SectionHeader
import com.elovo.ravnchallenge.presentation.ui.theme.MediumPadding
import com.elovo.ravnchallenge.presentation.ui.theme.RavnTypography
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
        },
        hasPadding = false
    ) {
        viewModel.person?.let {
            PersonDetails(viewModel = viewModel)
        }
        if (viewModel.isLoading) {
            LoadingCell(message = stringResource(id = R.string.common_loading))
        }
        if (viewModel.errorOccurred) {
            NoticeCell(message = stringResource(id = R.string.common_failed_to_load_data))
        }
    }
}

@Composable
private fun PersonDetails(viewModel: PersonViewModel) {
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
    FavoriteButton(viewModel = viewModel)
}

@Composable
fun FavoriteButton(viewModel: PersonViewModel) {
    val isFavorite = viewModel.person?.isFavorite ?: false
    val text = if (isFavorite) R.string.cd_remove_favorite else R.string.cd_add_favorite
    val icon = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MediumPadding),
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = { viewModel.onEvent(PersonEvent.OnUpdateFavoriteStatus(!isFavorite)) }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = stringResource(id = text)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = text),
                style = RavnTypography.p1Default
            )
        }
    }
}
