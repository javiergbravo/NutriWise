package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jgbravo.nutriwise.ui.api.theme.RedStop
import com.jgbravo.nutriwise.ui.feature.models.UiText

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
    TextField(
        modifier = modifier,
        label = { Text(text = label.asString()) },
        value = text,
        singleLine = isSingleLine,
        onValueChange = onTextChange,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(
                    modifier = modifier,
                    text = error.asString(),
                    color = RedStop
                )
            }
        },
        trailingIcon = {
            if (error != null)
                Icon(Filled.Error, "error icon", tint = MaterialTheme.colorScheme.error)
        },
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun SimpleFilledTextFieldSamplePreview() {
    FilledTextField(
        label = UiText.DynamicString("Objetivo"),
        text = "Pérdida de grasa",
        onTextChange = {},
        error = UiText.DynamicString("Error"),
    )
}