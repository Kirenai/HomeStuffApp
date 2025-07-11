package me.kire.re.homestuffapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.R
import me.kire.re.homestuffapp.domain.model.Category

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    navigateToCategory: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 16.dp)
            .clickable { category.route?.let { navigateToCategory(it) } },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceBright)
                .padding(12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(
                    width = category.iconWidth,
                    height = category.iconHeight
                ),
                painter = painterResource(category.icon),
                contentDescription = "Category Icon",
                tint = MaterialTheme.colorScheme.secondary
            )
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
                text = "${category.size} items",
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = Category(
            name = "Food",
            size = 12,
            icon = R.drawable.ic_food,
            iconWidth = 24.dp,
            iconHeight = 24.dp
        ),
        navigateToCategory = {}
    )
}