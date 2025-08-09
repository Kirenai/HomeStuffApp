package me.re.homestuffapp.presentation.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.re.homestuffapp.domain.model.CategoryWithItemCount
import me.re.homestuffapp.presentation.common.SwipeToDeleteItem
import me.re.homestuffapp.presentation.home.HomeEvent

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categories: List<CategoryWithItemCount>,
    navigateToCategory: (Long) -> Unit,
    event: (HomeEvent) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = categories,
        ) { category ->
            SwipeToDeleteItem(
                onEventDelete = {
                    event(HomeEvent.OnDeleteCategory(categoryId = category.categoryId))
                },
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