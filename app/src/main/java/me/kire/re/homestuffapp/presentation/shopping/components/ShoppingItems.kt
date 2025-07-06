package me.kire.re.homestuffapp.presentation.shopping.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.kire.re.homestuffapp.domain.model.Shopping

@Composable
fun ShoppingItems(
    modifier: Modifier = Modifier,
    shoppingItems: List<Shopping> = emptyList(),
    navigateToEdit: (Shopping) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(shoppingItems) {
            ShoppingItem(
                item = it,
                navigateToEdit = navigateToEdit
            )
        }
    }
}