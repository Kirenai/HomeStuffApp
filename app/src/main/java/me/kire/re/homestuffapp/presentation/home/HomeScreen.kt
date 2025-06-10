package me.kire.re.homestuffapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    navigateToNourishment: () -> Unit,
) {
    Column {
        Button(onClick = { navigateToNourishment() }) {
            Text(text = "Nourishment")
        }
    }
}