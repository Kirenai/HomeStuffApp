package me.re.homestuffapp.presentation.product.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.model.enums.UnitType
import me.re.homestuffapp.domain.model.enums.toShortString
import me.re.homestuffapp.presentation.common.DiscardAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    product: Product,
    onDismiss: () -> Unit,
    onProductUpdated: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product.name) }
    var description by remember { mutableStateOf(product.description) }
    var amountPerUnit by remember { mutableStateOf(product.amountPerUnit.toString()) }
    var unit by remember { mutableStateOf(product.unit) }

    var discard by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    DiscardAlertDialog(
        isDiscard = discard,
        onDismissRequest = { discard = false },
        onConfirmClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (sheetState.isVisible.not()) {
                    onDismiss()
                }
            }
        }
    )

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
                            if (name != product.name
                                || description != product.description
                                || amountPerUnit != product.amountPerUnit.toString()
                                || unit != product.unit
                            ) {
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
                                    onProductUpdated(
                                        Product(
                                            productId = product.productId,
                                            name = name,
                                            description = description,
                                            amountPerUnit = amountPerUnit.toFloat(),
                                            unit = unit,
                                            categoryId = product.categoryId,
                                        )
                                    )
                                }
                            }
                        },
                        enabled = !name.isEmpty()
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
                    text = product.name,
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
                        text = "Name",
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
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeholder = {
                            Text(
                                text = "Name of the product",
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

            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Description",
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
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        placeholder = {
                            Text(
                                text = "Description of the product",
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

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .selectableGroup()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Quantity",
                        modifier = Modifier
                            .height(32.dp),
                        style = MaterialTheme.typography.titleMedium.copy(
                            lineHeight = 24.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    )

                    Row(
                        modifier = Modifier
                            .height(48.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .weight(1f),
                            value = amountPerUnit,
                            onValueChange = {
                                amountPerUnit = it
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            placeholder = {
                                Text("15")
                            },
                            shape = MaterialTheme.shapes.medium,
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodySmall,
                            suffix = {
                                Text(
                                    text = unit.toShortString(),
                                    modifier = Modifier.padding(end = 8.dp),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        )

                        Box(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium),
                        ) {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More options"
                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                UnitType.entries.forEach { unitType ->
                                    DropdownMenuItem(
                                        text = { Text(unitType.name) },
                                        onClick = {
                                            unit = unitType
                                            expanded = false
                                        }
                                    )

                                    if (unitType != UnitType.entries.last()) {
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
