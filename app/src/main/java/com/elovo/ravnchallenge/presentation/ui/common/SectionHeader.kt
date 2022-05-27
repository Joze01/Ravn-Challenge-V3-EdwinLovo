package com.elovo.ravnchallenge.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.elovo.ravnchallenge.ui.theme.ExtraLargePadding
import com.elovo.ravnchallenge.ui.theme.MediumPadding
import com.elovo.ravnchallenge.ui.theme.RavnTypography
import com.elovo.ravnchallenge.ui.theme.SmallPadding

@Composable
fun SectionHeader(message: String) {
    Text(
        text = message,
        style = RavnTypography.h2Default,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding)
            .padding(top = ExtraLargePadding)
            .padding(bottom = SmallPadding)
    )
}
