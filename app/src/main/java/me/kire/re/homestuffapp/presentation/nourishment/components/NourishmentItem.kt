package me.kire.re.homestuffapp.presentation.nourishment.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import me.kire.re.homestuffapp.R
import me.kire.re.homestuffapp.domain.model.Nourishment

@Composable
fun NourishmentItem(
    modifier: Modifier = Modifier,
    nourishment: Nourishment,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick?.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = if (nourishment.imageUrl.isNotEmpty()) {
                    ImageRequest.Builder(context = LocalContext.current)
                        .data(nourishment.imageUrl)
                        .build()
                } else R.drawable.ic_launcher_foreground,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column {
            Text(
                text = nourishment.name,
                style = TextStyle.Default.copy(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 24.sp
                )
            )
            Text(
                text = nourishment.stock.toString(),
                style = TextStyle.Default.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 21.sp
                )
            )
        }
    }
}