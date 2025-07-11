package me.kire.re.homestuffapp.presentation.shopping.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.presentation.shopping.ShoppingEvent

@Composable
fun ShoppingItem(
    modifier: Modifier = Modifier,
    item: Shopping,
    navigateToEdit: (Shopping) -> Unit,
    event: (ShoppingEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .clickable { navigateToEdit(item) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.itemName,
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = buildAnnotatedString {
                        append("Store: ${item.store ?: "--"} | ")
                        append("Price: ${item.price ?: "--"} | ")
                        append("Quantity: ${item.quantity ?: "--"}")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        lineHeight = 21.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    softWrap = true,
                    overflow = TextOverflow.Clip
                )
//                Text(
//                    text = item.itemName,
//                    style = TextStyle.Default.copy(
//                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                        fontWeight = FontWeight.Normal,
//                        lineHeight = 21.sp
//                    )
//                )
            }
            Column {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.End),
                    onClick = { event(ShoppingEvent.DeleteItem(item)) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Item",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingItemPreview() {
    ShoppingItem(
        item = Shopping(
            shoppingId = "1",
            itemName = "Toilet Paper",
            store = "Discount Warehouse",
            price = "$18.75",
            quantity = "3"
        ),
        navigateToEdit = {},
        event = {}
    )
}