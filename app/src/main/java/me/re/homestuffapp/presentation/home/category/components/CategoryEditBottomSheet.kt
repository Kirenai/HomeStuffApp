package me.re.homestuffapp.presentation.home.category.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.re.homestuffapp.R
import me.re.homestuffapp.domain.model.Category
import me.re.homestuffapp.domain.model.CategoryWithItemCount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEditBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    category: CategoryWithItemCount,
    onDismiss: () -> Unit,
    onCategoryUpdated: (Category) -> Unit
) {
    var storeName by remember { mutableStateOf(category.name) }
    var discard by remember { mutableStateOf(false) }

    if (discard) {
        AlertDialog(
            onDismissRequest = {
                discard = false
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
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (sheetState.isVisible.not()) {
                                onDismiss()
                            }
                        }
                    }
                ) {
                    Text("Discard")
                }
            },
            dismissButton = {
                Button(
                    onClick = { discard = false }
                ) {
                    Text("Keep Editing")
                }
            }
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = MaterialTheme.shapes.large,
        containerColor = MaterialTheme.colorScheme.surface,
        dragHandle = { BottomSheetDefaults.HiddenShape }

    ) {
        Column(Modifier.fillMaxWidth()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Category",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (storeName != category.name) {
                                discard = true
                            } else {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (sheetState.isVisible.not()) {
                                        onDismiss()
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_arrow_back_ios_new_24),
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        shape = MaterialTheme.shapes.medium,
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (sheetState.isVisible.not()) {
                                    onCategoryUpdated(
                                        Category(
                                            categoryId = category.categoryId,
                                            name = storeName,
                                        )
                                    )
                                }
                            }
                        },
                        enabled = !storeName.isEmpty()
                    ) {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                },
            )
        }

        HorizontalDivider()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Category Name",
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            lineHeight = 24.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    )

                    TextField(
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(),
                        value = storeName,
                        onValueChange = {
                            storeName = it
                        },
                        placeholder = {
                            Text(
                                text = "Food",
                                style = MaterialTheme.typography.bodySmall,
                            )
                        },
                        maxLines = 1,
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        ),
                        textStyle = MaterialTheme.typography.bodySmall,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                    )
                }
            }
        }
    }
}
