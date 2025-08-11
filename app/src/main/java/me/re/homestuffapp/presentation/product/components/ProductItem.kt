package me.re.homestuffapp.presentation.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import me.re.homestuffapp.R
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.presentation.product.form.ProductEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (() -> Unit)? = null,
    event: (ProductEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it == SheetValue.Hidden

        }
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ProductEditBottomSheet(
            sheetState = sheetState,
            scope = scope,
            product = product,
            onDismiss = { showBottomSheet = false },
            onProductUpdated = { newProduct ->
                event(ProductEvent.OnUpdateProduct(newProduct))
                showBottomSheet = false
            }
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick?.invoke() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = if (product.hasImageUrl()) {
                    ImageRequest.Builder(context = LocalContext.current)
                        .data(product.imageUrl)
                        .build()
                } else R.drawable.ic_launcher_foreground,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column {
            Text(
                text = product.name,
                style = TextStyle.Default.copy(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 24.sp
                )
            )
            Text(
                text = product.concatenateAmountAndUnit(),
                style = TextStyle.Default.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 21.sp
                )
            )
        }

        // create a icon button for edit product in the end of the row
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceBright)
                    .padding(4.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize(),
                    onClick = {
                        println("Edit Product $product")
                        showBottomSheet = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit Category",
                    )
                }
            }
        }
    }
}