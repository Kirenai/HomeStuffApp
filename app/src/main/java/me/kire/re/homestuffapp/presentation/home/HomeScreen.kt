package me.kire.re.homestuffapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.domain.model.categories
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.home.components.CategoryList

@Composable
fun HomeScreen() {
    var text by remember {
        mutableStateOf("")
    }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SearchBar(
                text = text,
                onChangeValue = { text = it },
                onSearch = { },
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
            categories = categories
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}