package me.kire.re.homestuffapp.presentation.shopping.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.R
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.presentation.shopping.ShoppingEvent

@Composable
fun ShoppingEditScreen(
    modifier: Modifier = Modifier,
    shopping: Shopping,
    event: (ShoppingEvent) -> Unit
) {
    var quantity by remember { mutableIntStateOf(shopping.quantity?.toInt() ?: 1) }
    var storeName by remember { mutableStateOf(shopping.store ?: "") }
    var price by remember { mutableStateOf(shopping.price ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Quantity",
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        lineHeight = 24.sp,
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { quantity = maxOf(1, quantity - 1) },
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.extraLarge)
                            .background(Color.LightGray)
                            .size(28.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(width = 18.dp, height = 24.dp),
                            painter = painterResource(R.drawable.outline_remove_24),
                            contentDescription = "Decrease quantity"
                        )
                    }
                    Text(
                        text = quantity.toString(), // Replace with actual quantity
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 20.sp,
                            lineHeight = 24.sp
                        )
                    )
                    IconButton(
                        onClick = { quantity = quantity.plus(1) },
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.extraLarge)
                            .background(Color.LightGray)
                            .size(28.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(width = 19.dp, height = 22.dp),
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Increase quantity",
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Store",
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        lineHeight = 24.sp,
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
                            text = "Store name",
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
                        imeAction = ImeAction.Next,
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
                    text = "Price",
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        lineHeight = 24.sp,
                    )
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    value = price,
                    onValueChange = {
                        price = it
                    },
                    placeholder = {
                        Text(
                            text = "0.00",
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
                        keyboardType = KeyboardType.Decimal
                    ),
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = {
                    event(
                        ShoppingEvent.EditItem(
                            shopping.copy(
                                quantity = quantity.toString(),
                                store = storeName,
                                price = price
                            )
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    "Save",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingEditScreenPreview() {
    ShoppingEditScreen(
        shopping = Shopping(
            shoppingId = "1",
            itemName = "Sample Item",
        ),
        event = {}
    )
}