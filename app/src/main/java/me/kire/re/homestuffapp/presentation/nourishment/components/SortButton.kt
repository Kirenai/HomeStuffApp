package me.kire.re.homestuffapp.presentation.nourishment.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortButton(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.primaryContainer
            ),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 5.5.dp,
                end = 5.5.dp,
                bottom = 8.dp
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sort"
                )
                Spacer(Modifier.padding(start = 8.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Sort Icon"
                )
            }
        }
    }
}