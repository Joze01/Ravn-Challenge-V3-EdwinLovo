package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elovo.ravnchallenge.ui.theme.DividerColor

@Composable
fun RavnDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        color = DividerColor,
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}
