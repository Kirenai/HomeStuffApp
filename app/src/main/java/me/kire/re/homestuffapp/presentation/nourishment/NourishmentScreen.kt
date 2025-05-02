package me.kire.re.homestuffapp.presentation.nourishment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kire.re.homestuffapp.presentation.common.SearchBar

@Composable
fun NourishmentScreen(
    navigateToHome: () -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(all = 6.dp)
    ) {
        Text(text = "Nourishments",
            style = MaterialTheme.typography.displayMedium
            )
        SearchBar(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 8.dp),
            text = text,
            onChangeValue = {
                text = it
            }
        )
        Button(onClick = navigateToHome) {
            Text(text = "Navigate to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NourishmentScreenPreview() {
    NourishmentScreen(navigateToHome = {})
}
