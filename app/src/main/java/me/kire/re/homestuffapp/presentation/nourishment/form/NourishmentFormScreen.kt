package me.kire.re.homestuffapp.presentation.nourishment.form

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import me.kire.re.homestuffapp.presentation.common.DisplayImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NourishmentFormScreen(
    event: (NourishmentFormEvent) -> Unit,
    state: NourishmentFormState
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val capturedImageUri = remember { mutableStateOf<Uri>(Uri.EMPTY) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            println("Before captured image value ${capturedImageUri.value}")
            capturedImageUri.value = uri
            println("After captured image value ${capturedImageUri.value}")
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(context, "Camera permission granted", Toast.LENGTH_SHORT)
                .show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT)
                .show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                if (capturedImageUri.value.path?.isNotEmpty() == true) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(8.dp, Color.Red),
                        contentAlignment = Alignment.Center
                    ) {
                        DisplayImage(
                            modifier = Modifier
                                .size(400.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            image = capturedImageUri.value,
                            contentDescription = "Selected Image",
                        )

                        val imageUrl = capturedImageUri.toString()
                        println("Image Url $imageUrl")
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = state.name,
                        onValueChange = { event(NourishmentFormEvent.NameChanged(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = {
                            Text("Name")
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        ),
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                        onClick = {
                            val permissionResult =
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                )
                            if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch(uri)
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                    ) {
                        Text(
                            text = "Capture Image From Camera",
                        )
                    }

                    OutlinedTextField(
                        value = state.description,
                        onValueChange = { event(NourishmentFormEvent.DescriptionChanged(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = {
                            Text("Description")
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        ),
                    )

                    val radioOptions = listOf("UNIT", "PERCENTAGE")
                    val (selectedOption, setSelectedOption) = remember {
                        mutableStateOf(radioOptions[0])
                    }
                    Column(
                        modifier = Modifier
                            .selectableGroup()
                            .padding(vertical = 8.dp)
                    ) {
                        radioOptions.forEach { text ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = {
                                            setSelectedOption(text)
                                        },
                                        role = Role.RadioButton
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        setSelectedOption(text)
                                    },
                                )
                                Text(text = text)
                            }
                        }
                    }

                    when (selectedOption) {
                        "UNIT" -> {
                            OutlinedTextField(
                                value = state.unit,
                                onValueChange = {
                                    event(
                                        NourishmentFormEvent.NourishmentTypeChanged(
                                            nourishmentType = "UNIT",
                                            value = it
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        defaultKeyboardAction(ImeAction.Done)
                                        event(NourishmentFormEvent.SaveNourishment)
                                    }
                                ),
                                placeholder = {
                                    Text("0")
                                },
                                shape = MaterialTheme.shapes.medium,
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                            )
                        }
                        "PERCENTAGE" -> {
                            OutlinedTextField(
                                value = state.percentage,
                                onValueChange = {
                                    event(
                                        NourishmentFormEvent.NourishmentTypeChanged(
                                            nourishmentType = "PERCENTAGE",
                                            value = it
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        defaultKeyboardAction(ImeAction.Done)
                                        event(NourishmentFormEvent.SaveNourishment)
                                    }
                                ),
                                placeholder = {
                                    Text("0.0")
                                },
                                shape = MaterialTheme.shapes.medium,
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = MaterialTheme.shapes.small,
                        onClick = {
                            event(NourishmentFormEvent.SaveNourishment)
                        }
                    ) {
                        Text(
                            text = "Save",
                        )
                    }
                }
            }
        }
    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

@Preview(showBackground = true)
@Composable
fun NourishmentFormScreenPreview() {
    NourishmentFormScreen(
        event = {},
        state = NourishmentFormState()
    )
}