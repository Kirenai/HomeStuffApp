package me.kire.re.homestuffapp.presentation.shopping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.domain.model.shoppingMock
import me.kire.re.homestuffapp.presentation.shopping.components.ShoppingItems

@Composable
fun ShoppingScreen(
    shoppingItems: List<Shopping>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        ShoppingItems(
            shoppingItems = shoppingItems
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    ShoppingScreen(
        shoppingItems = shoppingMock
    )
}