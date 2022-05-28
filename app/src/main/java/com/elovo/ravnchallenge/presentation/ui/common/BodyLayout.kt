package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elovo.ravnchallenge.presentation.ui.theme.LargePadding
import com.elovo.ravnchallenge.presentation.ui.theme.NoPadding

@Composable
fun BodyLayout(
    modifier: Modifier = Modifier,
    hasPadding: Boolean = true,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    header: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        header()
        Column(
            modifier = modifier
                .padding(if (hasPadding) LargePadding else NoPadding),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            body()
        }
    }
}
