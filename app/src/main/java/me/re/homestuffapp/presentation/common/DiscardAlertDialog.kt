package me.re.homestuffapp.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DiscardAlertDialog(
    modifier: Modifier = Modifier,
    isDiscard: Boolean,
    onDismissRequest: () -> Unit,
    onHideClick: () -> Unit,
) {
    if (isDiscard) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text(
                    text = "Discard Changes",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to discard the changes?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onHideClick()
                    }
                ) {
                    Text("Discard")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Keep Editing")
                }
            },
        )
    }
}
