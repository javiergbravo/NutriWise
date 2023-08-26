package com.jgbravo.nutriwise.ui.feature.screens.dashboard

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jgbravo.nutriwise.common.app.models.PlanState.ACTIVE
import com.jgbravo.nutriwise.common.app.models.PlanState.END
import com.jgbravo.nutriwise.common.app.models.PlanState.STOPPED
import com.jgbravo.nutriwise.common.utils.DateTimeUtil
import com.jgbravo.nutriwise.common.utils.DateTimeUtil.minusMonths
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.CreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardEvent.OnMealPlanClicked
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.components.MealPlanItem

@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        val message = state.error?.asString(context) ?: return@LaunchedEffect
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        onEvent(OnErrorScreen)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(CreateMealPlan) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(25.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new plan"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.plans.size) { index ->
                val mealPlan = state.plans[index]
                MealPlanItem(
                    mealPlan = mealPlan,
                    onMealPlanClick = { onEvent(OnMealPlanClicked(mealPlan)) }
                )
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    val today = DateTimeUtil.now().date
    DashboardScreen(
        state = DashboardState(
            plans = listOf(
                MealPlan(
                    id = "1",
                    person = "John Doe",
                    startDate = today,
                    goal = "Pérdida de grasa",
                    kcal = 2600,
                    meals = emptyList(),
                    state = ACTIVE
                ),
                MealPlan(
                    id = "2",
                    person = "John Doe",
                    startDate = today.minusMonths(3),
                    goal = "Volumen",
                    kcal = 3000,
                    meals = emptyList(),
                    state = STOPPED
                ),
                MealPlan(
                    id = "3",
                    person = "Pérdida de grasa",
                    startDate = today.minusMonths(6),
                    goal = "This is a description",
                    kcal = 2300,
                    meals = emptyList(),
                    state = END
                )
            )
        ),
        onEvent = {}
    )
}