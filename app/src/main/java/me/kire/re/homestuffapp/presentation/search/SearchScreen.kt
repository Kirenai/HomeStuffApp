package me.kire.re.homestuffapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kire.re.homestuffapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    navigateToDetails: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 10.dp, end = 10.dp)
    ) {
        SearchBar(
            text = "",
            onChangeValue = {},
            onSearch = {}
        )
    }
}