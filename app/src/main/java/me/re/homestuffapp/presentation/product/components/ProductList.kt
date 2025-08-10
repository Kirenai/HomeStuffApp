package me.re.homestuffapp.presentation.product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import me.re.homestuffapp.domain.model.Product

/**
 * Unused, but kept for reference.
 */
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onClick: (Product) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = products.size
        ) {
            products[it].let { product ->
                ProductItem(
                    product = product,
                    onClick = { onClick(product) }
                )
            }
        }
    }
}

/**
 * Unused, but kept for reference.
 */
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    nourishments: LazyPagingItems<Product>,
    onClick: (Product) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(
            count = nourishments.itemCount,
            key = nourishments.itemKey { it.productId!! }
        ) {
            nourishments[it]?.let { product: Product ->
                ProductItem(
                    product = product,
                    onClick = { onClick(product) }
                )
            }
        }
    }
}

