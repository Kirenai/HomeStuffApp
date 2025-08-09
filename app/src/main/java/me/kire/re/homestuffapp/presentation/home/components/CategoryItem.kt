package me.kire.re.homestuffapp.presentation.home.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.R
import me.kire.re.homestuffapp.domain.model.CategoryWithItemCount
import me.kire.re.homestuffapp.domain.model.categories
import me.kire.re.homestuffapp.presentation.home.category.components.CategoryEditBottomSheet

fun Modifier.shimmerEffect(cornerRadius: CornerRadius = CornerRadius(x = 12f, y = 12f)) = composed {
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "transparency of the background color"
    ).value
    val color = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = cornerRadius
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryWithItemCount,
    navigateToCategory: (Long) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        CategoryEditBottomSheet(
            sheetState = sheetState,
            scope = scope,
            category = category,
            onDismiss = { showBottomSheet = false },
            onCategoryUpdated = {
                showBottomSheet = false
            }
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .height(72.dp)
            .padding(horizontal = 16.dp)
            .clickable { navigateToCategory(category.categoryId) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceBright)
                .padding(12.dp)
                .shimmerEffect(),
            contentAlignment = Alignment.Center,
        ) {
//            Icon(
//                modifier = Modifier.size(
//                    width = 24.dp,
//                    height = 24.dp
//                ),
//                painter = painterResource(category.icon),
//                contentDescription = "Category Icon",
//                tint = MaterialTheme.colorScheme.secondary
//            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = category.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )
            Text(
                text = "${category.itemsCount} items",
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

         // create a icon button for edit category in the end of the row
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
                        println("Edit Category $category")
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

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = categories[0],
        navigateToCategory = {}
    )
}