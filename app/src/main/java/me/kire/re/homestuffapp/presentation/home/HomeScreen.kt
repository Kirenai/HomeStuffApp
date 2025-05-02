package me.kire.re.homestuffapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    navigateToNourishment: () -> Unit,
) {
    Column {
        Box {
            Text(text = "Home", fontSize = 32.sp)
        }
        Button(onClick = { navigateToNourishment() }) {
            Text(text = "Nourishment")
        }
    }
}