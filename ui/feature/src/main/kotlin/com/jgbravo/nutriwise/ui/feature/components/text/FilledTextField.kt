package com.jgbravo.nutriwise.ui.feature.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jgbravo.nutriwise.ui.api.theme.RedStop
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.models.UiText.DynamicString

@Composable
fun FilledTextField(
    label: UiText,
    text: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    error: UiText? = null,
    isSingleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label.asString()) },
        isError = error != null,
        singleLine = isSingleLine,
        supportingText = {
            if (error != null) {
                Text(
                    modifier = modifier,
                    text = error.asString(),
                    color = RedStop
                )
            }
        },
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun SimpleFilledTextFieldSamplePreview() {
    FilledTextField(
        label = DynamicString("Objetivo"),
        text = "Pérdida de grasa",
        onTextChange = {},
        error = DynamicString("Error"),
    )
}