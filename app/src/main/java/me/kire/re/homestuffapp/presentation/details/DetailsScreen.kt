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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import me.kire.re.homestuffapp.data.entity.PurchaseEntity
import me.kire.re.homestuffapp.domain.model.Product
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.presentation.details.components.LineChartWithGradient
import me.kire.re.homestuffapp.presentation.details.components.PurchaseItem
import me.kire.re.homestuffapp.presentation.shopping.ShoppingEvent

@Composable
fun DetailsScreen(
    product: Product,
    navigateToShopping: () -> Unit,
    isAlreadyAdded: Boolean = false,
    event: (ShoppingEvent) -> Unit,
    lastTwoPurchases: List<PurchaseEntity>,
    charData: List<Float>,
) {
    println("Last two purchases size: ${lastTwoPurchases.size}")
    println("Chard data size: ${charData.size}")
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
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(218.dp),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(product.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = product.name,
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
                        text = product.description,
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

                if (lastTwoPurchases.isNotEmpty()) {
                    lastTwoPurchases.forEach { purchase ->
                        PurchaseItem(
                            store = purchase.storeName,
                            quantity = "${purchase.weightKg}",
                            price = "$${purchase.price}"
                        )
                    }
                } else {
                    Text(
                        text = "No purchase history available.",
                        style = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 24.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }
//                PurchaseItem(
//                    store = "Fresh Market",
//                    quantity = "2 kg",
//                    price = "$10.00"
//                )
//
//                PurchaseItem(
//                    store = "Local Grocer",
//                    quantity = "1.5 kg",
//                    price = "$1.75"
//                )

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Price per pound",
                            style = TextStyle.Default.copy(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 24.sp
                            )
                        )

                        Text(
                            text = product.name,
                            style = TextStyle.Default.copy(
                                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 40.sp
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Last 3 Months",
                                style = TextStyle.Default.copy(
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 24.sp,
                                    color = Color(0xFF737373)
                                )
                            )
                            Text(
                                text = "-10%",
                                style = TextStyle.Default.copy(
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 24.sp,
                                    color = Color(0xFFE80808)
                                )
                            )
                        }

                        val chartData = listOf(
                            1.3f,
                            1.4f,
                            1.25f,
                            1.35f,
                            1.1f,
                            1.6f,
                            1.3f,
                            1.5f,
                            1.3f,
                            1.3f,
                            1.4f,
                            1.25f,
                            1.35f,
                            1.1f,
                            1.4f,
                        )
                        val xLabels = listOf("Jan", "Feb", "Mar")
                        LineChartWithGradient(
                            data = chartData,
                            xLabels = xLabels,
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Button(
                        onClick = {
                            if (!isAlreadyAdded) {
                                event(
                                    ShoppingEvent.AddItem(
                                        Shopping(
                                            shoppingId = product.productId,
                                            itemName = product.name,
                                            productId = product.productId
                                        )
                                    )
                                )
                                navigateToShopping()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        enabled = !isAlreadyAdded
                    ) {
                        Text(
                            "Add to Shopping List",
                            style = TextStyle.Default.copy(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        product = Product(
            productId = 1,
            name = "Orange",
            stock = 3,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/1728/1728765.png",
            description = "Fresh orange",
            expirationDate = "5 days",
            isAvailable = true,
            categoryId = 1L
        ),
        navigateToShopping = {},
        event = {},
        lastTwoPurchases = emptyList(),
        charData = emptyList()
    )
}