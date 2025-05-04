package me.kire.re.homestuffapp.presentation.nourishment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kire.re.homestuffapp.domain.model.Nourishment

@Composable
fun NourishmentList(
    modifier: Modifier = Modifier,
    nourishments: List<Nourishment>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(
            count = nourishments.size
        ) {
            nourishments[it].let { nourishment ->
                NourishmentCard(nourishment = nourishment, onClick = {})
            }
        }
    }
}

