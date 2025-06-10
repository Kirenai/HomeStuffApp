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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import me.kire.re.homestuffapp.domain.model.Nourishment
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
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(nourishment.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = nourishment.name,
                        style = MaterialTheme.typography.displayMedium

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

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Description"
                            )
                            Text(
                                text = nourishment.description,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                }
            }
        }
    }
}