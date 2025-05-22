package me.kire.re.homestuffapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun DisplayImage(
    modifier: Modifier = Modifier,
    image: Any,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(image)
            .crossfade(enable = true)
            .build(),
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}