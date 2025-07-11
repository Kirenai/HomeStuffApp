package me.kire.re.homestuffapp.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.presentation.Dimens.IconBoxSize

@Composable
fun PurchaseItem(
    modifier: Modifier = Modifier,
    store: String,
    quantity: String,
    price: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 13.5.dp, bottom = 13.5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            ) {
                Box(
                    modifier = Modifier
                        .size(IconBoxSize)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.surfaceBright),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
                Column {
                    Text(
                        text = store,
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 24.sp
                        )
                    )
                    Text(
                        text = quantity,
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            lineHeight = 21.sp
                        )
                    )
                }

            }
        }
        Column {
            Text(
                text = price,
                style = TextStyle.Default.copy(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = 24.sp
                )
            )
        }
    }
}