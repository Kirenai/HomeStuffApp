package me.kire.re.homestuffapp.presentation.nourishment.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import me.kire.re.homestuffapp.domain.model.Nourishment

@Composable
fun NourishmentCard(
    modifier: Modifier = Modifier,
    nourishment: Nourishment,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick?.invoke() },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(nourishment.imageUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = nourishment.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                )
                Row {
                    Text(
                        text = "${nourishment.stock} in stock",
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1
                    )
                }
            }
        }
    }
}