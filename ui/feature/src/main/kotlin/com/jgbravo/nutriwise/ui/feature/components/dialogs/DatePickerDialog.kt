package com.jgbravo.nutriwise.ui.feature.components.dialogs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgbravo.common.app.dates.DateTimeUtil
import com.jgbravo.nutriwise.ui.feature.R.string
import com.jgbravo.nutriwise.ui.feature.models.UiText.StringResource
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDialog(
    onConfirmDate: (LocalDateTime) -> Unit,
    onDismiss: () -> Unit,
    initialDate: LocalDateTime = DateTimeUtil.now(),
    startDate: LocalDateTime? = null
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = DateTimeUtil.toEpochMillis(initialDate)
    )
    DatePickerDialog(
        shape = RoundedCornerShape(6.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millisSelected ->
                        onConfirmDate(
                            DateTimeUtil.getLocalDateTimeFromMillis(millisSelected)
                        )
                    }
                }
            ) {
                Text(text = StringResource(string.button_continue).asString())
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
            dateValidator = { timestamp ->
                if (startDate != null) {
                    timestamp >= DateTimeUtil.toEpochMillis(startDate)
                } else {
                    true
                }
            }
        )
    }
}

@Preview
@Composable
fun CalendarDialogPreview() {
    CalendarDialog(
        onConfirmDate = {},
        onDismiss = {}
    )
}