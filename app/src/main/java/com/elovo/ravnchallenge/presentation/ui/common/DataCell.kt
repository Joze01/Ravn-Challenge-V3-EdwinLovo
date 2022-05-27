package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elovo.ravnchallenge.presentation.utils.capitalize
import com.elovo.ravnchallenge.ui.theme.MediumPadding
import com.elovo.ravnchallenge.ui.theme.RavnTypography

@Composable
fun DataCell(
    modifier: Modifier = Modifier,
    label: String,
    value: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = MediumPadding)
                .padding(vertical = MediumPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = RavnTypography.h2LowEmphasis
            )
            value?.let {
                Text(
                    text = value.capitalize(),
                    style = RavnTypography.h2Default
                )
            }
        }
        RavnDivider()
    }
}
