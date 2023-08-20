package com.jgbravo.nutriwise.ui.dashboard

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgbravo.nutriwise.ui.dashboard.composables.MealItem
import com.jgbravo.nutriwise.ui.dashboard.models.Meal
import com.jgbravo.nutriwise.ui.dashboard.models.MealType.AFTERNOON_SNACK
import com.jgbravo.nutriwise.ui.dashboard.models.MealType.BREAKFAST
import com.jgbravo.nutriwise.ui.dashboard.models.MealType.DINNER
import com.jgbravo.nutriwise.ui.dashboard.models.MealType.LUNCH
import com.jgbravo.nutriwise.ui.dashboard.models.MealType.MORNING_SNACK

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        val message = state.error ?: return@LaunchedEffect
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        onEvent(DashboardEvent.OnErrorScreen)
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(state.meals.size) { index ->
                    val meal = state.meals[index]
                    MealItem(
                        meal = meal,
                        backgroundColor = Color.White,
                        onMealClick = { onEvent(DashboardEvent.OnMealClicked(meal)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        state = DashboardState(
            meals = listOf(
                Meal(
                    mealType = BREAKFAST,
                    carbs = 20,
                    protein = 10,
                    fat = 5
                ),
                Meal(
                    mealType = MORNING_SNACK,
                    carbs = 20,
                    protein = 10,
                    fat = 5
                ),
                Meal(
                    mealType = LUNCH,
                    carbs = 20,
                    protein = 10,
                    fat = 5
                ),
                Meal(
                    mealType = AFTERNOON_SNACK,
                    carbs = 20,
                    protein = 10,
                    fat = 5
                ),
                Meal(
                    mealType = DINNER,
                    carbs = 20,
                    protein = 10,
                    fat = 5
                )
            )
        ),
        onEvent = {}
    )
}