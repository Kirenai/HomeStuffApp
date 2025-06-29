package me.kire.re.homestuffapp.presentation.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.kire.re.homestuffapp.domain.model.Category

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categories: List<Category>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = categories,
        ) { category ->
            CategoryItem(
                category = category
            )
        }

    }
}