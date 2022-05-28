package com.elovo.ravnchallenge.presentation.ui.people.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.elovo.domain.model.Homeworld
import com.elovo.domain.model.Person
import com.elovo.ravnchallenge.R
import com.elovo.ravnchallenge.presentation.ui.common.RavnDivider
import com.elovo.ravnchallenge.presentation.ui.theme.MediumPadding
import com.elovo.ravnchallenge.presentation.ui.theme.NoPadding
import com.elovo.ravnchallenge.presentation.ui.theme.RavnChallengeTheme
import com.elovo.ravnchallenge.presentation.ui.theme.RavnTypography

@Composable
fun PersonCell(
    modifier: Modifier = Modifier,
    hasPaddingDivider: Boolean = true,
    person: Person,
    onClick: (String) -> Unit
) {
    val specie = person.species?.name ?: stringResource(id = R.string.person_human)
    val homeworld = person.homeworld?.name ?: stringResource(id = R.string.person_unknown)

    Column(
        modifier = modifier
            .clickable { onClick(person.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = person.name ?: "",
                    style = RavnTypography.h2Default
                )
                Text(
                    text = stringResource(
                        id = R.string.person_from, specie, homeworld
                    ),
                    style = RavnTypography.p1LowEmphasis
                )
            }
            IconButton(onClick = { onClick(person.id) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = stringResource(
                        id = R.string.cd_select_person, person.name ?: ""
                    )
                )
            }
        }
        RavnDivider(
            modifier = Modifier.padding(start = if (hasPaddingDivider) MediumPadding else NoPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonCellPreview() {
    RavnChallengeTheme {
        PersonCell(
            person = Person(
                id = "12",
                name = "Luke Skywalker",
                homeworld = Homeworld(
                    id = "1",
                    name = "Tatooine"
                )
            ),
            onClick = { }
        )
    }
}
