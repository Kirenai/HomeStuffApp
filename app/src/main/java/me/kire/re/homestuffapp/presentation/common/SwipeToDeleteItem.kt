package me.kire.re.homestuffapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Componente para eliminar un elemento con un gesto de deslizamiento.
 * Este componente muestra un diálogo de confirmación antes de eliminar el elemento.
 * @param modifier Modificador opcional para personalizar el estilo del componente.
 * @param onEventDelete Función que se llama cuando se confirma la eliminación del elemento.
 * @param postfix Cadena que se muestra en el diálogo de confirmación para indicar el tipo de elemento a eliminar.
 * @param content Contenido que se muestra dentro del componente de deslizamiento.
 */
@Composable
fun SwipeToDeleteItem(
    modifier: Modifier = Modifier,
    onEventDelete: () -> Unit,
    postfix: String,
    content: @Composable () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    val dismissState: SwipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                showDialog = true
                true
            }
            false
        },
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(
                    "Delete $postfix",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    "Are you sure you want to delete this ${postfix}?",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onEventDelete()
                }) {
                    Text(
                        "Delete",
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(
                        "Cancel",
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                    )
                }
            }
        )
    }

    SwipeToDismissBox(
        modifier = modifier,
        state = dismissState,
        backgroundContent = {
            val color = when (dismissState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.onError
                else -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }
        },
    ) {
        content.invoke()
    }

}