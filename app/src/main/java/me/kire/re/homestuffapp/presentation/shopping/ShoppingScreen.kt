package me.kire.re.homestuffapp.presentation.shopping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.domain.model.shoppingMock
import me.kire.re.homestuffapp.presentation.shopping.components.ShoppingItems

@Composable
fun ShoppingScreen(
    shoppingItems: List<Shopping>,
    navigateToEdit: (Shopping) -> Unit,
    event: (ShoppingEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxHeight()
    ) {
        ShoppingItems(
            shoppingItems = shoppingItems,
            navigateToEdit = navigateToEdit
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = { event(ShoppingEvent.MarkAsPurchased) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = shoppingItems.isNotEmpty()
            ) {
                Text(
                    "Mark as Purchased",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingScreenPreview() {
    ShoppingScreen(
        shoppingItems = shoppingMock,
        navigateToEdit = {},
        event = {}
    )
}