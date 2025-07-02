package me.kire.re.homestuffapp.presentation.nourishment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.nourishment.components.NourishmentList
import me.kire.re.homestuffapp.presentation.nourishment.components.SortButton

@Composable
fun NourishmentScreen(
    nourishments: LazyPagingItems<Nourishment>,
    navigateToDetails: (Nourishment) -> Unit,
    navigateToSearch: () -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            text = text,
            onChangeValue = {
                text = it
            },
            onSearch = navigateToSearch
        )

        Spacer(modifier = Modifier.height(24.dp))

        SortButton()

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 0.dp, top = 16.dp, bottom = 8.dp),
            ) {
                Text(
                    text = "In Stock",
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        NourishmentList(
            nourishments = listOf(
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
                    name = "Orange",
                    stock = 3,
                    imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
                    description = "Fresh orange",
                    isAvailable = true,
                )
            ),
            onClick = navigateToDetails
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 0.dp, top = 16.dp, bottom = 8.dp),
            ) {
                Text(
                    text = "Out of Stock",
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        NourishmentList(
            nourishments = listOf(
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
                    name = "Orange",
                    stock = 3,
                    imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
                    description = "Fresh orange",
                    isAvailable = true,
                )
            ),
            onClick = navigateToDetails
        )
//        NourishmentList(
//            nourishments = nourishments,
//            onClick = navigateToDetails
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun NourishmentScreenPreview() {
    NourishmentScreen(
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
        ).collectAsLazyPagingItems()
    )
}
