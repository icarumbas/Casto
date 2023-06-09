package com.icarumbas.casto.android.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ListItemHeadline(text: String, modifier: Modifier = Modifier) {
    val style = AppTypography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = text,
        modifier = modifier,
        style = style,
    )
}

@Composable
fun ListItemSupporting(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    val style = AppTypography.bodyMedium.copy(
        color = color
    )
    Text(
        text = text,
        modifier = modifier,
        style = style,
    )
}