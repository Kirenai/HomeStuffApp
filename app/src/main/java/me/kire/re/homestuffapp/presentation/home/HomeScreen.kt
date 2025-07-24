package me.kire.re.homestuffapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import me.kire.re.homestuffapp.domain.model.categories
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.home.components.CategoryList

@Composable
fun HomeScreen(
    event: (HomeEvent) -> Unit,
    state: HomeState,
    navigateToCategory: (Long) -> Unit,
    error: MutableLiveData<String>?,
    clearCategoryError: () -> Unit,
) {
    LaunchedEffect(error?.value) {
        error?.value?.let { message ->
            event(HomeEvent.OnError(message))

            clearCategoryError()
        }
    }

    if (state.error != null) {
        AlertDialog(
            onDismissRequest = { event(HomeEvent.OnErrorDismissed) },
            title = { Text(text = "Error") },
            text = { Text(text = state.error) },
            confirmButton = {
                TextButton(
                    onClick = { event(HomeEvent.OnErrorDismissed) }
                ) {
                    Text(
                        text = "OK",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        )
    }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
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
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                lineHeight = 23.sp
            ),
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
        navigateToCategory = {},
        error = MutableLiveData<String>(null),
        clearCategoryError = { }
    )
}