package me.kire.re.homestuffapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import me.kire.re.homestuffapp.domain.model.Category
import me.kire.re.homestuffapp.ui.theme.IconBoxDark
import me.kire.re.homestuffapp.ui.theme.IconBoxLight

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    navigateToCategory: (String) -> Unit
) {
    val iconBoxColor = if (isSystemInDarkTheme()) {
        IconBoxLight
    } else IconBoxDark


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
                .background(iconBoxColor)
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
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${category.size} items",
                fontSize = 14.sp,
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
            icon = me.kire.re.homestuffapp.R.drawable.ic_food,
            size = 12,
            iconWidth = 24.dp,
            iconHeight = 24.dp
        ),
        navigateToCategory = {}
    )
}