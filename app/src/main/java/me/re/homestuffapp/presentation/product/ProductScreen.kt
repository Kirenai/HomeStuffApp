package me.re.homestuffapp.presentation.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.presentation.common.SearchBar
import me.re.homestuffapp.presentation.common.SwipeToDeleteItem
import me.re.homestuffapp.presentation.product.components.ProductItem
import me.re.homestuffapp.presentation.product.components.SortButton
import me.re.homestuffapp.presentation.product.form.ProductEvent

@Composable
fun ProductScreen(
    products: LazyPagingItems<Product>,
    navigateToDetails: (Product) -> Unit,
    navigateToSearch: () -> Unit,
    event: (ProductEvent) -> Unit,
) {
    var searchText by remember {
        mutableStateOf("")
    }

    products.itemKey {
        println("Item key: ${it.productId}")
    }

    var isSorted by remember {
        mutableStateOf(false)
    }

    val filteredProduct = remember(products.itemSnapshotList.items, searchText) {
        products.itemSnapshotList.items.filter {
            it.name.contains(searchText, ignoreCase = true)
        }
    }

    println("Filtered products: ${filteredProduct.size}")

    val groped = filteredProduct.groupBy { it.isAvailable }

    val inStockList = groped[true].orEmpty()
    val outOfStockList = groped[false].orEmpty()

    val displayList = if (isSorted) {
        listOfNotNull(
            if (outOfStockList.isNotEmpty()) "Out of Stock" to outOfStockList else null,
            if (inStockList.isNotEmpty()) "In Stock" to inStockList else null
        )
    } else {
        listOfNotNull(
            if (inStockList.isNotEmpty()) "In Stock" to inStockList else null,
            if (outOfStockList.isNotEmpty()) "Out of Stock" to outOfStockList else null,
        )
    }

    var showLoading by remember { mutableStateOf(true) }
    val isLoading = products.loadState.refresh is LoadState.Loading

    LaunchedEffect(isLoading) {
        if (isLoading) {
            showLoading = true
        } else {
            delay(timeMillis = 500)
            showLoading = false
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        // Mostrar loading al menos 0.5 segundos
        if (showLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return
        }

        SearchBar(
            text = searchText,
            onChangeValue = {
                searchText = it
            },
            onSearch = navigateToSearch
        )

        Spacer(modifier = Modifier.height(24.dp))

        SortButton(
            onClick = {
                isSorted = !isSorted
            }
        )

        if (displayList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No products found",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            return
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            displayList.forEach { (title, items) ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 0.dp, top = 16.dp, bottom = 8.dp),
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }

                items(items, key = { it.productId!! }) { product: Product ->
                    SwipeToDeleteItem(
                        onEventDelete = {
                            event(ProductEvent.OnDeleteProduct(productId = product.productId))
                        },
                        postfix = "Product"
                    ) {
                        ProductItem(
                            product = product,
                            onClick = {
                                navigateToDetails(product)
                            },
                            event = event,
                        )
                    }
                }

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen(
        navigateToDetails = {},
        navigateToSearch = {},
        products = flowOf(
            PagingData.from(
                listOf(
                    Product(
                        productId = 1,
                        name = "Orange",
                        stock = 3,
                        imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
                        description = "Fresh orange",
                        isAvailable = true,
                        categoryId = 1L,
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        event = {}
    )
}
