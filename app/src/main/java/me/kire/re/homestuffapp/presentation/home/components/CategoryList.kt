package me.kire.re.homestuffapp.presentation.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.kire.re.homestuffapp.domain.model.CategoryWithItemCount
import me.kire.re.homestuffapp.presentation.common.SwipeToDeleteItem

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categories: List<CategoryWithItemCount>,
    navigateToCategory: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = categories,
        ) { category ->
            SwipeToDeleteItem(
                onEventDelete = {},
                postfix = "Category",
            ) {
                CategoryItem(
                    category = category,
                    navigateToCategory = navigateToCategory
                )
            }
        }

    }
}