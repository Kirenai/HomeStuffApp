package me.kire.re.homestuffapp.presentation.nourishment

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun NourishmentScreen() {
    Column {
        Text(text = "Nourishment", fontSize = 32.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun NourishmentScreenPreview() {
    NourishmentScreen()
}
