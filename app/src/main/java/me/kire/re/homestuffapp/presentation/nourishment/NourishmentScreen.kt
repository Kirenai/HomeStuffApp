package me.kire.re.homestuffapp.presentation.nourishment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.presentation.common.SearchBar
import me.kire.re.homestuffapp.presentation.nourishment.components.NourishmentList

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
            .padding(top = 16.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = "Nourishments",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        SearchBar(
            text = text,
            onChangeValue = {
                text = it
            },
            onSearch = navigateToSearch
        )
        Spacer(modifier = Modifier.height(24.dp))
//        NourishmentList(
//            nourishments = listOf(
//                Nourishment(
//                    nourishmentId = "1",
//                    name = "Orange",
//                    stock = 3,
//                    imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
//                    description = "Fresh orange",
//                    expirationDate = "5 days",
//                    isAvailable = true,
//                ),
//                Nourishment(
//                    nourishmentId = "2",
//                    name = "Orange",
//                    stock = 3,
//                    imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
//                    description = "Fresh orange",
//                    isAvailable = true,
//                )
//            ),
//            onClick = navigateToDetails
//        )
        NourishmentList(
            nourishments = nourishments,
            onClick = navigateToDetails
        )
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
