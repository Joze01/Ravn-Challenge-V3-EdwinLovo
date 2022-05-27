package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elovo.ravnchallenge.ui.theme.LightTextColor
import com.elovo.ravnchallenge.ui.theme.MediumPadding
import com.elovo.ravnchallenge.ui.theme.RavnTypography
import com.elovo.ravnchallenge.ui.theme.SmallPadding

@Composable
fun LoadingCell(
    modifier: Modifier = Modifier,
    message: String
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(MediumPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(horizontal = SmallPadding)
                .size(16.dp),
            strokeWidth = 2.dp,
            color = LightTextColor
        )
        Text(text = message, style = RavnTypography.h2LowEmphasis)
    }
}
