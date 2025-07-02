package me.kire.re.homestuffapp.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.presentation.details.components.PurchaseItem
import me.kire.re.homestuffapp.presentation.details.components.TextStock

@Composable
fun DetailsScreen(
    nourishment: Nourishment
) {
    LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(218.dp),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(nourishment.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = nourishment.name,
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 28.sp
                        )

                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 12.dp)
                ) {
                    Text(
                        text = nourishment.description,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Purchase History",
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 23.sp
                        )

                    )
                }

                PurchaseItem(
                    store = "Fresh Market",
                    quantity = "2 kg",
                    price = "$10.00"
                )

                PurchaseItem(
                    store = "Local Grocer",
                    quantity = "1.5 kg",
                    price = "$1.75"
                )

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Pricing Trends",
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 23.sp
                        )

                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "In Stock"
                            )
                            nourishment.type?.unit?.let {
                                TextStock(
                                    isAvailable = nourishment.isAvailable,
                                    stock = it
                                )
                            }
                            nourishment.type?.percentage?.let {
                                TextStock(
                                    isAvailable = nourishment.isAvailable,
                                    stock = it
                                )
                            }
                        }
                    }

                    nourishment.expirationDate?.let { expirationDate ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Expires"
                                )
                                Text(
                                    text = "In $expirationDate",
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        nourishment = Nourishment(
            nourishmentId = "1",
            name = "Orange",
            stock = 3,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            expirationDate = "5 days",
            isAvailable = true,
        )
    )
}