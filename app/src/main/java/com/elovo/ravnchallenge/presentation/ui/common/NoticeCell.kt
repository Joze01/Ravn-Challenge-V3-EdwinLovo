package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elovo.ravnchallenge.presentation.ui.theme.MediumPadding
import com.elovo.ravnchallenge.presentation.ui.theme.RavnTypography

@Composable
fun NoticeCell(
    modifier: Modifier = Modifier,
    message: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MediumPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            style = RavnTypography.h2HighEmphasis
        )
    }
}
