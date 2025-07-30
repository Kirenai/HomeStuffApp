package me.kire.re.homestuffapp.presentation.nourishment

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.nourishment.components.ProductItem
import me.kire.re.homestuffapp.presentation.nourishment.components.SortButton

@Composable
fun ProductScreen(
    nourishments: LazyPagingItems<Nourishment>,
    navigateToDetails: (Nourishment) -> Unit,
    navigateToSearch: () -> Unit,
    categoryId: Long?,
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var isSorted by remember {
        mutableStateOf(false)
    }

    val loadedItems = listOf(
        Nourishment(
            nourishmentId = "1",
            name = "Orange",
            stock = 3,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            expirationDate = "5 days",
            isAvailable = true,
        ),
        Nourishment(
            nourishmentId = "2",
            name = "Apple",
            stock = 3,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            isAvailable = true,
        ),
        Nourishment(
            nourishmentId = "3",
            name = "Banana",
            stock = 0,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            expirationDate = "5 days",
            isAvailable = false,
        ),
        Nourishment(
            nourishmentId = "4",
            name = "Strawberry",
            stock = 0,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            isAvailable = false,
        )
    )

    val filtered = loadedItems.filter { it.name.contains(searchText, ignoreCase = true) }

    val groped = filtered.groupBy { it.isAvailable }

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

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
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

                items(items) { nourishment ->
                    ProductItem(
                        nourishment = nourishment,
                        onClick = { navigateToDetails(nourishment) }
                    )
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
        nourishments = flowOf(
            PagingData.from(
                listOf(
                    Nourishment(
                        nourishmentId = "1",
                        name = "Orange",
                        stock = 3,
                        imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
                        description = "Fresh orange",
                        isAvailable = true,
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        categoryId = null
    )
}
