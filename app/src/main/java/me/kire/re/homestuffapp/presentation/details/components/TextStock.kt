package me.kire.re.homestuffapp.presentation.details.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextStock(
    isAvailable: Boolean,
    stock: Any,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = if (isAvailable) "$stock left" else "Not available",
        fontWeight = FontWeight.SemiBold
    )
}
