package me.kire.re.homestuffapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.domain.model.categories
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.home.components.CategoryList

@Composable
fun HomeScreen(
    event: (HomeEvent) -> Unit,
    state: HomeState,
    navigateToCategory: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SearchBar(
                text = state.searchText,
                onChangeValue = { event(HomeEvent.OnSearchTextChange(it)) },
                onSearch = {
                },
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Categories",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        CategoryList(
            categories = state.filteredCategories,
            navigateToCategory = navigateToCategory
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        event = {},
        state = HomeState(
            categories = categories
        ),
        navigateToCategory = {}
    )
}