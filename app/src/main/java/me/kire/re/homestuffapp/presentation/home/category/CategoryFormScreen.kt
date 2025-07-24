package me.kire.re.homestuffapp.presentation.home.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kire.re.homestuffapp.domain.model.Category

@Composable
fun CategoryFormScreen(
    modifier: Modifier = Modifier,
    event: (CategoryFormEvent) -> Unit,
    navigateUp: (String?) -> Unit,
    state: CategoryState
) {
    var storeName by remember { mutableStateOf("") }

    LaunchedEffect(state.error, state.success) {
        if (state.success) {
            navigateUp(null)
            event(CategoryFormEvent.ClearSuccess)
        }

        state.error?.let { error ->
            navigateUp(error)
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Category Name",
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                )

                TextField(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    value = storeName,
                    onValueChange = {
                        storeName = it
                    },
                    placeholder = {
                        Text(
                            text = "Food",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    maxLines = 1,
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodySmall,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = {
                    event(
                        CategoryFormEvent.OnSaveCategory(
                            category = Category(
                                name = storeName
                            )
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    "Save Category",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}