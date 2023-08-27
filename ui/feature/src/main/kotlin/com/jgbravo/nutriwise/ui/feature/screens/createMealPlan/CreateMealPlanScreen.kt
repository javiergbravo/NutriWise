package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.R.string
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.ClickBack
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.ClickCreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnDismissBottomSheet
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnGoalChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnKcalChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnPersonNameChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnStartDateChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.components.FilledTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMealPlanScreen(
    state: CreateMealPlanState,
    onEvent: (CreateMealPlanEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create new meal plan",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(ClickBack) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ClickCreateMealPlan)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(25.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Add new plan"
                )
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                FilledTextField(
                    label = UiText.StringResource(string.person_name),
                    text = state.personName,
                    onTextChange = { onEvent(OnPersonNameChanged(it)) },
                    error = state.personNameError,
                    isSingleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FilledTextField(
                    label = UiText.StringResource(string.goal),
                    text = state.goal,
                    onTextChange = { onEvent(OnGoalChanged(it)) },
                    error = state.goalError,
                    isSingleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FilledTextField(
                    label = UiText.StringResource(string.start_date),
                    text = state.startDate,
                    onTextChange = { onEvent(OnStartDateChanged(it)) },
                    isSingleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FilledTextField(
                    label = UiText.StringResource(string.kcal),
                    text = state.kcal,
                    onTextChange = { onEvent(OnKcalChanged(it)) },
                    error = state.kcalError,
                    isSingleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }

        if (state.isBottomSheetOpened) {
            val sheetState = rememberModalBottomSheetState()
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    onEvent(OnDismissBottomSheet)
                    onEvent(ClickBack)
                },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = UiText.StringResource(string.your_meal_plan_has_been_created_successfully).asString(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = {
                            onEvent(OnDismissBottomSheet)
                            onEvent(ClickBack)
                        }
                    ) {
                        Text(text = UiText.StringResource(R.string.button_continue).asString())
                    }
                }
            }
        }

    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun CreateMealPlanScreenPreview() {
    CreateMealPlanScreen(
        state = CreateMealPlanState(),
        onEvent = {}
    )
}