package me.re.homestuffapp.presentation.shopping.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.re.homestuffapp.domain.model.Shopping
import me.re.homestuffapp.presentation.shopping.ShoppingEvent

@Composable
fun ShoppingItems(
    modifier: Modifier = Modifier,
    shoppingItems: List<Shopping> = emptyList(),
    navigateToEdit: (Shopping) -> Unit,
    event: (ShoppingEvent) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(shoppingItems) {
            ShoppingItem(
                item = it,
                navigateToEdit = navigateToEdit,
                event = event
            )
        }
    }
}